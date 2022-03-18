package com.dilo.gmall.payment.service;

import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.model.payment.PaymentInfo;

import java.util.Map;

public interface PaymentService {

    /**
     * 保存交易记录
     * @param orderInfo
     * @param paymentType
     */
    void savePaymentInfo(OrderInfo orderInfo,String paymentType);

    /**
     *根据商户订单号查询交易记录
     * @param outTradeNo
     * @param name
     * @return
     */
    PaymentInfo getPaymentInfo(String outTradeNo, String name);

    /**
     * 更新交易记录
     * @param outTradeNo
     * @param name
     * @param paramsMap
     */
    void paySuccess(String outTradeNo, String name, Map<String, String> paramsMap);

    void updatePayMentInfo(String outTradeNo, String name, PaymentInfo paymentInfo);


}
