package com.dilo.gmall.payment.service;

public interface AlipayService {
    //支付:返回值为什么是一个字符串
    String createAliPay(Long orderId);

    /**
     * 退款服务
     * @param orderId
     * @return
     */
    boolean refund(Long orderId);
}
