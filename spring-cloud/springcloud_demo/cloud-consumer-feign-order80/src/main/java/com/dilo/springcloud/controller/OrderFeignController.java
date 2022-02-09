package com.dilo.springcloud.controller;

import com.dilo.springcloud.bean.Book;
import com.dilo.springcloud.bean.CommonResult;
import com.dilo.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Book> getPaymentById(@PathVariable("id") int id){
        return paymentFeignService.getPaymentById(id);

    }
}
