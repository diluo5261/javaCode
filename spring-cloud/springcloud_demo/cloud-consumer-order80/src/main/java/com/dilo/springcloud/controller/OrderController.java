package com.dilo.springcloud.controller;

import com.dilo.springcloud.bean.Book;
import com.dilo.springcloud.bean.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    //public static final String PAYMENT_URL="http://127.0.0.1:8001";
    public static final String PAYMENT_URL="http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;


    @PostMapping("/consumer/payment/create")
    public CommonResult<Book> creat(Book book){
        CommonResult commonResult = restTemplate.postForObject(PAYMENT_URL + "/payment/creat", book, CommonResult.class);

        return commonResult;

    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Book> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }



}
