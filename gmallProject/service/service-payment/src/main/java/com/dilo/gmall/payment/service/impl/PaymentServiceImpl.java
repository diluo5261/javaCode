package com.dilo.gmall.payment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public void paySuccess(String outTradeNo, String name, Map<String, String> paramsMap) {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setTradeNo(paramsMap.get("trade_no"));
        paymentInfo.setPaymentStatus(PaymentStatus.PAID.name());
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent(paramsMap.toString());

        this.updatePayMentInfo(outTradeNo, name, paymentInfo);

    }


    @Override
    public void updatePayMentInfo(String outTradeNo, String name, PaymentInfo paymentInfo) {
        QueryWrapper<PaymentInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_trade_no", outTradeNo).eq("payment_type", name);

        paymentInfoMapper.update(paymentInfo,queryWrapper);
    }
}
