package com.dilo.gmall.payment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dilo.gmall.common.constant.MqConst;
import com.dilo.gmall.common.service.RabbitService;
import com.dilo.gmall.model.enums.PaymentStatus;
import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.model.payment.PaymentInfo;
import com.dilo.gmall.order.client.OrderFeignClient;
import com.dilo.gmall.payment.mapper.PaymentInfoMapper;
import com.dilo.gmall.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;
    @Autowired
    private RabbitService rabbitService;

    @Override
    public void savePaymentInfo(OrderInfo orderInfo, String paymentType) {

        //1.先查询一下,看支付信息是否已经存在
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",orderInfo.getId());
        queryWrapper.eq("payment_type",paymentType);
        Integer count = paymentInfoMapper.selectCount(queryWrapper);
        if (count > 0) return;


        //保存交易记录
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setOutTradeNo(orderInfo.getOutTradeNo());
        paymentInfo.setOrderId(orderInfo.getId());
        paymentInfo.setTotalAmount(orderInfo.getTotalAmount());
        paymentInfo.setPaymentStatus(PaymentStatus.UNPAID.name());
        paymentInfo.setSubject(orderInfo.getTradeBody());
        paymentInfo.setPaymentType(paymentType);
        paymentInfoMapper.insert(paymentInfo);
    }

    @Override
    public PaymentInfo getPaymentInfo(String outTradeNo, String name) {
        return paymentInfoMapper.selectOne(new QueryWrapper<PaymentInfo>().eq("out_trade_no",outTradeNo).eq("payment_type",name));

    }

    //name:支付类型
    @Override
    public void paySuccess(String outTradeNo, String name, Map<String, String> paramsMap) {
        //交易的记录状态如果是ClOSED,PAID,则直接返回不需要处理
        PaymentInfo paymentInfoQuery = this.getPaymentInfo(outTradeNo, name);
        //判断状态
        if ("CLOSED".equals(paymentInfoQuery.getPaymentStatus())||"PAID".equals(paymentInfoQuery.getPaymentStatus())){
            return;
        }


        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setTradeNo(paramsMap.get("trade_no"));
        paymentInfo.setPaymentStatus(PaymentStatus.PAID.name());
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(paramsMap.toString());

        this.updatePayMentInfo(outTradeNo, name, paymentInfo);
        //发送消息给订单!
        rabbitService.sendMessage(MqConst.EXCHANGE_DIRECT_PAYMENT_PAY,MqConst.ROUTING_PAYMENT_PAY,paymentInfoQuery.getOrderId());

    }


    @Override
    public void updatePayMentInfo(String outTradeNo, String name, PaymentInfo paymentInfo) {
        //构建条件
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_trade_no", outTradeNo).eq("payment_type", name);

        paymentInfoMapper.update(paymentInfo,queryWrapper);
    }

    @Override
    public void closePayment(Long orderId) {
        //一个参数标识要修改的内容,第二个参数标识要更新的条件
        UpdateWrapper<PaymentInfo> paymentInfoUpdateWrapper = new UpdateWrapper<>();
        paymentInfoUpdateWrapper.eq("order_id",orderId);

        //判断是否有数据
        Integer count = paymentInfoMapper.selectCount(paymentInfoUpdateWrapper);
        if (count == 0){
            return;
        }

        //关闭交易记录,payment_status
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaymentStatus(PaymentStatus.ClOSED.name());
        paymentInfoMapper.update(paymentInfo,paymentInfoUpdateWrapper);
    }
}
