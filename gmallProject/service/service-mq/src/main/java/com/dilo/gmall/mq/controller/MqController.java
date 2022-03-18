package com.dilo.gmall.mq.controller;

import com.dilo.gmall.mq.config.DeadLetterMqConfig;
import com.dilo.gmall.common.result.Result;
import com.dilo.gmall.common.service.RabbitService;
import com.dilo.gmall.mq.config.DelayedMqConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/mq")

public class MqController {

    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //发送消息的映射路径
    @GetMapping("sendConfirm")
    public Result sendConfirm(){
        //发送消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        rabbitService.sendMessage("exchange.confirm", "routing.confirm8888888",format);
        return Result.ok();
    }

    @GetMapping("sendDeadLettle")
    public Result sendDeadLettle(){
        //设置发送的消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        System.out.println("发送时间:"+format);
        rabbitService.sendMessage(DeadLetterMqConfig.exchange_dead,DeadLetterMqConfig.routing_dead_1,format);
        return Result.ok();
    }

    @GetMapping("sendDelay")
    public Result sendDelay(){
        //设置发送的消息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        System.out.println("发送时间:"+format);

        //设置发送信息!使用插件的时候不需要再队列中设置,需要在发送的时候设置
        //rabbitService.sendMessage(DeadLetterMqConfig.exchange_dead,DeadLetterMqConfig.routing_dead_1,format);
        rabbitTemplate.convertAndSend(DelayedMqConfig.exchange_delay, DelayedMqConfig.routing_delay, "来的真早,享受服务", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(1000);
                System.out.println("消息发送时间---"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                return message;
            }
        });
        return Result.ok();
    }
}
