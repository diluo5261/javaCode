package com.dilo.gmall.order.service.impl;

import com.alibaba.fastjson.JSON;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
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
    public void execExpiredOrder(Long orderId) {
        //执行update语句
        updateOrderStatus(orderId,ProcessStatus.CLOSED);
        //后续可能需要更新订单进度状态,还需要更新订单状态
        //void updateOrderStatus(Long orderId, ProcessStatus processStatus)

        //发送一个消息关闭交易记录
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_PAYMENT_CLOSE,MqConst.ROUTING_PAYMENT_CLOSE,orderId);
    }

    @Override
    public void updateOrderStatus(Long orderId, ProcessStatus processStatus) {
        OrderInfo orderInfo = new OrderInfo();
        //从订单进度状态中获取订单状态
        orderInfo.setOrderStatus(processStatus.getOrderStatus().name());
        orderInfo.setProcessStatus(processStatus.name());
        orderInfo.setId(orderId);

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




    @Override
    public void sendOrderStatus(Long orderId) {
        //更改订单状态
        this.updateOrderStatus(orderId,ProcessStatus.NOTIFIED_WARE);
        String wareJson = initWareOrder(orderId);
        //发送消息
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_WARE_STOCK,MqConst.ROUTING_WARE_STOCK,wareJson);

    }

    /**
     * 发送减库存消息的字符串
     * @param orderId
     * @return
     */
    private String initWareOrder(Long orderId) {
        //发送的字符串是有OrderInfo中部分字段组成的
        OrderInfo orderInfo = this.getOrderInfo(orderId);

        //将orderInfo中的数据组成Map
        Map map = initWareOrder(orderInfo);

        return JSON.toJSONString(map);

    }

    @Override
    public Map initWareOrder(OrderInfo orderInfo) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId", orderInfo.getId());
        map.put("consignee", orderInfo.getConsignee());
        map.put("consigneeTel", orderInfo.getConsigneeTel());
        map.put("orderComment", orderInfo.getOrderComment());
        map.put("orderBody", orderInfo.getTradeBody());
        map.put("deliveryAddress", orderInfo.getDeliveryAddress());
        map.put("paymentWay", "2");
        map.put("wareId", orderInfo.getWareId());// 仓库Id ，减库存拆单时需要使用！

         /*
            details:[{skuId:101,skuNum:1,skuName:
            ’小米手64G’},
            {skuId:201,skuNum:1,skuName:’索尼耳机’}]
     */
        
        //先获取订单明细
        List<OrderDetail> mapDetailList = orderInfo.getOrderDetailList();
        ArrayList<Map> mapArrayList = new ArrayList<>();
        for (OrderDetail orderDetail : mapDetailList) {
            HashMap<String, Object> orderDetailMap = new HashMap<>();
            orderDetailMap.put("skuId", orderDetail.getSkuId());
            orderDetailMap.put("skuNum", orderDetail.getSkuNum());
            orderDetailMap.put("skuName", orderDetail.getSkuName());
            mapArrayList.add(orderDetailMap);
        }
        map.put("details",mapArrayList);
        return map;
    }

    //获取所有子订单集合
    @Override
    public List<OrderInfo> orderSplit(String orderId, String wareSkuMap) {
        /*
            1.先获取到原始订单
            2.根据wareSkuMap转换成map,拆单
            3.生成新的子订单,并赋值,保存子订单
            4.将子订单添加到集合中!
            5.修改原始订单状态
            6.
         */

        ArrayList<OrderInfo> subOrderInfoList = new ArrayList<>();
        OrderInfo orderInfo = this.getOrderInfo(Long.parseLong(orderId));

        List<Map> maps = JSON.parseArray(wareSkuMap, Map.class);
        if (!CollectionUtils.isEmpty(maps)){
            for (Map map : maps) {
                //仓库id
                String wareId = (String) map.get("wareId");
                //获取到skuId列表
                List<String> skuIds = (List<String>) map.get("skuIds");

                OrderInfo subOrderInfo = new OrderInfo();
                BeanUtils.copyProperties(orderInfo,subOrderInfo);

                //总金额{跟订单明细有关系},父Id
                subOrderInfo.setParentOrderId(Long.parseLong(orderId));

                //id自增
                subOrderInfo.setId(null);

                //赋值仓库id
                subOrderInfo.setWareId(wareId);

                //计算金额需要商品的订单明细
                ArrayList<OrderDetail> orderDetails = new ArrayList<>();
                List<OrderDetail> orderDetailList = orderInfo.getOrderDetailList();
                //循环遍历
                for (OrderDetail orderDetail : orderDetailList) {
                    for (String skuId : skuIds) {
                        //比较skuId
                        if (orderDetail.getSkuId() == Long.parseLong(orderId)){
                            //得到当前仓库的订单明细
                            orderDetails.add(orderDetail);
                        }
                    }
                }
                //给子订单赋值订单明细
                subOrderInfo.setOrderDetailList(orderDetails);
                subOrderInfo.sumTotalAmount();

                //保存子订单
                saveOrderInfo((subOrderInfo));
                subOrderInfoList.add(subOrderInfo);
            }
        }

        //修改原始订单状态
        this.updateOrderStatus(Long.parseLong(orderId),ProcessStatus.SPLIT);

        return subOrderInfoList;
    }

    @Override
    public void execExpiredOrder(Long orderId, String flag) {
        //执行update语句
        updateOrderStatus(orderId,ProcessStatus.CLOSED);

        if ("2".equals(flag)){
            //发送一个消息关闭交易记录
            rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_PAYMENT_CLOSE,MqConst.ROUTING_PAYMENT_CLOSE,orderId);
        }
    }
}
