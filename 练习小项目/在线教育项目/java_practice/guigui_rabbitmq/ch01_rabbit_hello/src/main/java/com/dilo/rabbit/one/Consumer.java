package com.dilo.rabbit.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消费者
public class Consumer {

    //接收消息
    public static void main(String[] args) throws IOException, TimeoutException {

        //创建工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.169.128");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        //创建连接
        Connection connection = factory.newConnection();

        //获取channel,有了信道就可以发送和接收消息
        Channel channel = connection.createChannel();


        //声明接收消息的表达式
        DeliverCallback deliverCallback = (consumerTag,message)->{
            System.out.println(new String(message.getBody()));
        };

        //取消消息时的对调
        CancelCallback callback = var1->{
            System.out.println("消息被中段");
        };

        //消费消息
        /*
            1.消费哪个队列
            2.消费成功后是否自动应答
            3.消费者为成功消费的回调
            4.消费者取消消费的回调

         */
        channel.basicConsume(Producer.QUEUE_NAME,true,deliverCallback,callback);

    }
}
