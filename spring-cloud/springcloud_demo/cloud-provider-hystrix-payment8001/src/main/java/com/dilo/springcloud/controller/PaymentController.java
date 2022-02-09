package com.dilo.springcloud.controller;

import com.dilo.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("******result"+result);
        return result;

    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        log.info("******result"+result);
        return result+port;

    }
}
