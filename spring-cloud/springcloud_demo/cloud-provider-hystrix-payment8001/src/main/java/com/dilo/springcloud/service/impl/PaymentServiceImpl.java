package com.dilo.springcloud.service.impl;

import com.dilo.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    //成功
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_OK成功!";
    }

    //失败
    @HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    @Override
    public String paymentInfo_Timeout(Integer id) {
        int timeNumber = 10;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_Timeout失败!";
    }

    @Override
    public String payment_TimeoutHandler(Integer id) {
        //兜底方法，上面方法出问题,我来处理，返回一个出错信息
        return "线程池"+Thread.currentThread().getName()+"payment_TimeoutHandler 系统繁忙,请稍后再试\t o(╥﹏╥)o ";
    }


}
