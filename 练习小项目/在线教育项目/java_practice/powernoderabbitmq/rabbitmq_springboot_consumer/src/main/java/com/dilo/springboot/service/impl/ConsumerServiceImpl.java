package com.dilo.springboot.service.impl;

import com.dilo.springboot.service.ConsumerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("consumerService")
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private AmqpTemplate amqpTemplate;
    RabbitTemplate rabbitTemplate;


    /*
    注解用于标记当前方法是一个 RabbitMq的消息监听方法,作用是持续性的自动接收消息
    这个方法不需要寿佛那个调用,spring会自动运行这个程序

    属性 queues 用于指定一个已经存在的队列名,用于进行队列的监听

    如果当前监听方法正常结束spring就会自动确认消息,如果出现异常则不会确认消息,
    因此在消息处理时我们要做好消息的防止重复处理工作
     */
    @Override
    @RabbitListener(queues = "bootDirectQueue")
    public void receive(String message) {

        //String message = (String) amqpTemplate.receiveAndConvert("bootDirectQueue");
        //System.out.println(message);



    }
}
