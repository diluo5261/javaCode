package com.dilo.rabbit.three;

import com.dilo.rabbit.one.Producer;
import com.dilo.rabbit.utils.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消息在手动应答时是不丢失的
public class Task02 {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtil.getChannel();

        //声明队列
        channel.queueDeclare(Producer.QUEUE_NAME,false,false,false,null);

        for (int i = 0; i <7 ; i++) {
            String msg = "第"+i+"条消息";
            //channel.basicPublish("",Producer.QUEUE_NAME,null,msg.getBytes());
            //设置生产者发送消息为持久化消息(要求保存到内存中)
            channel.basicPublish("",Producer.QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,msg.getBytes());
        }

        System.out.println("消息全部发送完成!");

    }
}
