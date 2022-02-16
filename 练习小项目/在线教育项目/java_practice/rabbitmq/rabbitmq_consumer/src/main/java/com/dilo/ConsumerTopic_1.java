package com.dilo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTopic_1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //1、创建连接工程工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //2、连接参数，虚拟机，端口等
        connectionFactory.setHost("192.168.169.128");//ip 默认值是 localhost
        connectionFactory.setPort(5672); //端口 ： 默认值15672
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest"); //默认值是 guest
        connectionFactory.setPassword("guest"); //密码默认值 guest

        //3、创建 Connection
        Connection connection = connectionFactory.newConnection();
        //4、创建 Channel
        Channel channel = connection.createChannel();



        //接收消息

        Consumer consumer = new DefaultConsumer(channel){
            /*
                回调方法，当接收消息后，会自动执行该方法
                1. consumerTag ： 标识
                2.envelope ：获取一些信息，交换机
                3.properties ： 配置信息
                4. body ： 数据
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("body："+new String(body));
                System.out.println("将日志信息存入数据库");
            }
        };

          /*
        1. queue : 队列名称
        2. autoAck ： 是否自动确认
        3. callback ： 回调对象
         */
        channel.basicConsume("test1_topic",true,consumer);

        //消费者相当于一个监听程序，不需要关闭程序

    }
}
