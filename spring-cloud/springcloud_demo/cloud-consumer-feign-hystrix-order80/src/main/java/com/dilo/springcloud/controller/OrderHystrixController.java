package com.dilo.springcloud.controller;

import com.dilo.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    //@HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
    //        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")//超过1.5秒就降级自己
    //})

    //@HystrixCommand
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        //int i = 10/0;
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("****result:"+result);
        return result;

    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String payment_Timeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.payment_Timeout(id);
        log.info("****result:"+result);
        return result;
    }

    //兜底方法，上面方法出问题,我来处理，返回一个出错信息
    public String payment_TimeoutHandler(Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒后再试。或自己运行出错，请检查自己。";
    }

    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }

}
