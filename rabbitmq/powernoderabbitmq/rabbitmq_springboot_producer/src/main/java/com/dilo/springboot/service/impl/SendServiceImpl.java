package com.dilo.springboot.service.impl;

import com.dilo.springboot.service.SendService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sendService")
public class SendServiceImpl implements SendService {

    //注入 Amqp 的模板类,使用模板类来发送消息
    @Resource
    private AmqpTemplate amqpTemplate;
    @Override
    public void sendMessage(String message) {

        /*
        参数一:交换机名
        参数二:为RoutingKey
        参数三 : 要发送的数据
         */
        amqpTemplate.convertAndSend("bootDirectExchange","bootDirectRouting",message);

    }
}
