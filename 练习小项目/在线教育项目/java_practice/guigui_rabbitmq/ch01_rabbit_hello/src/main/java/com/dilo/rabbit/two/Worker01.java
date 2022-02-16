package com.dilo.rabbit.two;

import com.dilo.rabbit.one.Producer;
import com.dilo.rabbit.utils.RabbitUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
    这是一个工作线程,相当于消费之
 */
public class Worker01 {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtil.getChannel();


        //声明接收消息的表达式
        DeliverCallback deliverCallback = (consumerTag, message)->{
            System.out.println("接收到的消息 : "+new String(message.getBody()));
        };

        //取消消息时的对调
        CancelCallback callback = var1->{
            System.out.println("消息被中断");
        };

        System.out.println(Thread.currentThread().getName()+"接收消息 >>>");
        channel.basicConsume(Producer.QUEUE_NAME,true, deliverCallback,callback);
    }


}
