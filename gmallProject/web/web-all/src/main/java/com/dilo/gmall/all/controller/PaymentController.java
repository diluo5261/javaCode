package com.dilo.gmall.all.controller;

import com.dilo.gmall.model.order.OrderInfo;
import com.dilo.gmall.order.client.OrderFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {

    @Autowired
    private OrderFeignClient orderFeignClient;

    @GetMapping("pay.html")
    public String pay(HttpServletRequest request){

        String orderId = request.getParameter("orderId");
        OrderInfo orderInfo = orderFeignClient.getOrderInfo(Long.parseLong(orderId));

        //保存对象
        request.setAttribute("orderInfo",orderInfo);
        return "payment/pay";
    }

    @GetMapping("/pay/success.html")
    public String paySuccess(){
        return "payment/success";
    }
}
