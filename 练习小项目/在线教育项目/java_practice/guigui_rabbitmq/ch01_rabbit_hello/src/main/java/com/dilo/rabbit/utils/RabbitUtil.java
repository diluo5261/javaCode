package com.dilo.rabbit.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitUtil {


    public static Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.169.128");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        //创建连接
        Connection connection = factory.newConnection();

        //获取channel,有了信道就可以发送和接收消息
        Channel channel = connection.createChannel();

        return channel;
    }
}
