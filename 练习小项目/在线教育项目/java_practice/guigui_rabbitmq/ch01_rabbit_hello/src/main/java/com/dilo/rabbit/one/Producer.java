package com.dilo.rabbit.one;

//第一步：先编写生产者，目的是发消息

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    //队列名称
    public static final String QUEUE_NAME = "guiguhello";

    //发消息
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //工厂 IP 连接 rabbitmq 的队列
        factory.setHost("192.168.169.128");
        //用户名
        factory.setUsername("guest");
        //密码
        factory.setPassword("guest");

        //创建连接,这里抛出一个异常
        Connection connection = factory.newConnection();

        //获取信道
        Channel channel = connection.createChannel();

        /*
            生成一个队列
            参数:
                1.队列名称
                2.队列李米娜的消息是否持久化(磁盘),没骗人情况下存储在内存中
                3.该队列是否只供一个消费者进行消费,是否进行消息共享
                4.是否自动删除,最后一个消费者断开连接后,该队列是否自动删除
                5.其他参数
         */

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //发送消息
        String msg = "hello rabbitmq!!";

        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        channel.close();
        connection.close();
   }
}
