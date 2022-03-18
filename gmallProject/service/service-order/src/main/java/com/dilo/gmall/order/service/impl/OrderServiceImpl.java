package com.dilo.gmall.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dilo.gmall.common.constant.MqConst;
import com.dilo.gmall.common.service.RabbitService;
import com.dilo.gmall.common.util.HttpClientUtil;
import com.dilo.gmall.model.enums.OrderStatus;
import com.dilo.gmall.model.enums.ProcessStatus;
import com.dilo.gmall.model.order.OrderDetail;
import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.order.mapper.OrderDetailMapper;
import com.dilo.gmall.order.mapper.OrderInfoMapper;
import com.dilo.gmall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo>implements OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitService rabbitService;

    @Value("${ware.url}")
    private String WARE_URL;

    @Override
    @Transactional
    public Long saveOrderInfo(OrderInfo orderInfo) {
        /*
        需要手动设置total_amount,user_id,out_trade_no,trade_body,create_time,expire_time,process_status
         */
        //自动赋值
        orderInfo.sumTotalAmount();
        orderInfo.setOrderStatus(OrderStatus.UNPAID.name());
        //在是实现类中,能否获取到userId,在控制器获取
        //第三方交易编号要求唯一!
        String outTradeNo = "dilo" + System.currentTimeMillis() + "" + new Random().nextInt(1000);
        orderInfo.setOutTradeNo(outTradeNo);
        //订单描述,可以将订单中的skuName进行拼接
        orderInfo.setTradeBody("有钱就想花");
        orderInfo.setCreateTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        orderInfo.setExpireTime(calendar.getTime());
        orderInfo.setOrderStatus(ProcessStatus.UNPAID.name());

        orderInfoMapper.insert(orderInfo);
        //获取到订单明细
        List<OrderDetail> orderDetailList = orderInfo.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {

            orderDetail.setOrderId(orderInfo.getId());
            orderDetailMapper.insert(orderDetail);
        }
        Long orderId = orderInfo.getId();
        rabbitService.sendDelayMessage(MqConst.EXCHANGE_DIRECT_ORDER_CANCEL,MqConst.ROUTING_ORDER_CANCEL,orderId,MqConst.DELAY_TIME);
        return orderId;
    }

    @Override
    public String getTradeNo(String userId) {
        //制作流水交易号
        String tradeNo = UUID.randomUUID().toString();

        //存储到缓存中
        String tradeNoKey = "user"+userId+"tradeCode";
        redisTemplate.opsForValue().set(tradeNoKey,tradeNo);

        return tradeNoKey;
    }

    @Override
    public boolean checkTradeNo(String userId, String tradeNo) {
        String tradeNoKey = "user"+userId+"tradeCode";
        String tradeNoRedis = (String) redisTemplate.opsForValue().get(tradeNoKey);
        return tradeNo.equals(tradeNoRedis);
    }

    @Override
    public void deleteTradeNo(String userId) {
        String tradeNoKey = "user"+userId+"tradeCode";
        redisTemplate.delete(tradeNoKey);
    }

    @Override
    public boolean checkStock(Long skuId, Integer skuNum) {
        String result = HttpClientUtil.doGet(WARE_URL+"/hasStock?skuId="+skuId+"&num"+skuNum);

        return "1".equals(result);
    }

    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        //根据订单id查询订单对象
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        //后续可能需要用到订单明细

        List<OrderDetail> orderDetailList = orderDetailMapper.selectList(new QueryWrapper<OrderDetail>().eq("order_id", orderId));
        orderInfo.setOrderDetailList(orderDetailList);

        //返回订单对象
        return orderInfo;
    }
}
