package com.dilo;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/*
    发送消息

 */
public class Producer_PubSub {
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

        //5、创建交换机
        /*
        public DeclareOk exchangeDeclare(String exchange, BuiltinExchangeType type,
        boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments)
         参数：
            1.exchange ： 交换机名称
            2.type ： 交换机类型
                    DIRECT("direct"), ：定向
                    FANOUT("fanout"), ： 扇形（广播），发送消息到每一个域治绑定队列
                    TOPIC("topic"), ： 通配符方式
                    HEADERS("headers"); ： 参数匹配

            3. durable ：是否持久化
            4.autoDelete ： 自动删除
            5.internal ： 内部使用，一般false
            6.arguments ： 参数

         */

        String exchangeName = "test_fanout";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT,true,false,false,null);

        //6、创建队列
        channel.queueDeclare("test1_fanout",true,false,false,null);
        channel.queueDeclare("test2_fanout",true,false,false,null);

        //7.绑定队列和交换机
        /*
        queueBind(String queue, String exchange, String routingKey)
        参数：
        queue : 队列名称
        exchange ： 交换机
        routingkey : 路由键，绑定规则，如果交换机的类型为 fanout，routingkey 设置为 “”，
        意思就是会把消息分发给每一个阈值绑定的队列


         */
        channel.queueBind("test1_fanout",exchangeName,"");
        channel.queueBind("test2_fanout",exchangeName,"");

        //8.发送消息
        String body = "日志信息：调用了方法，日志级别 info";
        channel.basicPublish(exchangeName,"",null,body.getBytes());


        //9.释放资源
        channel.close();
        connection.close();


    }
}
