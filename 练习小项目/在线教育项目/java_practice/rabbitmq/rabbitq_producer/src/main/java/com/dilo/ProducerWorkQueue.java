package com.dilo;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
    发送消息

 */
public class ProducerWorkQueue {
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

        //5、创建队列，Queue


        /*
            1.queue : 队列名称
            2. durable ： 是否持久化，当 mq 重启后，还在
            3. exclusive ： 是否独占，只能有一个次奥给这这监听这个队列，当Connection关闭时，是否删除队列
            4. autoDelete ： 是否自动删除
            5. arguments ： 其他参数
         */

        //如果没有一个名字叫做 hello 的队列，则该队列会创建，如果有则不会创建
        channel.queueDeclare("workQueue",false,false,false,null);

        //6、发送消息
        /*
        1. exchange : 交换机名称，简单模式下交互机会使用默认的 “”
        2. routingKey : 路由名称
        3. props ： 配置信息
        4. body ： 发送数据消息
         */

        for (int i = 0; i < 10; i++) {

            String msg = i+"hello rabbitmq777！！！！！";
            channel.basicPublish("","workQueue",null,msg.getBytes());
        }


        //7.释放资源：
        channel.close();
        connection.close();

    }
}
