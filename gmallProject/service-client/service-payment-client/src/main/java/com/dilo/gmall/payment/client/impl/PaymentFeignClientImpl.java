package com.dilo.gmall.payment.client.impl;

import com.dilo.gmall.model.payment.PaymentInfo;
import com.dilo.gmall.payment.client.PaymentFeignClient;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignClientImpl implements PaymentFeignClient {

    @Override
    public Boolean closePay(Long orderId) {
        return null;
    }

    @Override
    public Boolean checkPayment(Long orderId) {
        return null;
    }

    @Override
    public PaymentInfo getPaymentInfo(String outTradeNo) {
        return null;
    }
}
