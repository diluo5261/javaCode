package com.dilo.springcloud.service;

public interface PaymentService {
    String paymentInfo_OK(Integer id);
    String paymentInfo_Timeout(Integer id);

    String payment_TimeoutHandler(Integer id);
}
