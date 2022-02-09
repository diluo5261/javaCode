package com.dilo.springcloud.service;

import com.dilo.springcloud.bean.Book;
import com.dilo.springcloud.bean.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Book> getPaymentById(@PathVariable("id") int id);
}
