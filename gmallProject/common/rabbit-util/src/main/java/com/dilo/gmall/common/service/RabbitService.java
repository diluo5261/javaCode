package com.dilo.gmall.common.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 发送消息
     * @param exchange  交换机
     * @param routingKey  路由键
     * @param message    消息
     * @return
     */
    public boolean sendMessage(String exchange,String routingKey,Object message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return true;
    }

    //发送延迟消息
    public  boolean sendDelayMessage(String exchange,String routingKey,Object message,int delayTime){
        //发送消息
        rabbitTemplate.convertAndSend(exchange, routingKey, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(delayTime*1000);
                return null;
            }
        });
        return true;
    }
}