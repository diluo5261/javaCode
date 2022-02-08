package com.dilo.springcloud.controller;

import com.dilo.springcloud.bean.Book;
import com.dilo.springcloud.bean.CommonResult;
import com.dilo.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/creat")
    public CommonResult<Book> creat(@RequestBody Book book){
        int result = paymentService.creat(book);
        log.info("插入结果"+result);
        if (result >0){
            return new CommonResult(200,"数据插入成功!",result);
        }else{
            return new CommonResult(444,"数据插入失败!");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Book> getPaymentById(@PathVariable("id") int id){
        System.out.println("port = " + port);
        Book book = paymentService.getParamById(id);
        log.info("查询结果"+book);

        if (book != null){
            return new CommonResult<>(200,"数据查询成功!",book);
        }else{
            return new CommonResult<>(444,"没有对应的记录,查询id"+id,null);
        }
    }
}
