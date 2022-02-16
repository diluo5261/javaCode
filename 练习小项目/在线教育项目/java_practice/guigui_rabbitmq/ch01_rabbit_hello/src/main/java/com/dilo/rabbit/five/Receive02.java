package com.dilo.rabbit.five;

import com.dilo.rabbit.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//接收消息
public class Receive02 {
    public static final String EXCHANGE_NAME="logs";
    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitUtil.getChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        //声明一个队列,临时队列
        /*
        生成一个临时队列\队列的名称是随机的
        当消费之断开与队列的连接的时候,队列就自动删除了
         */

        String queueName = channel.queueDeclare().getQueue();

        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("02接收到的数据"+new String(body));
            }
        };

        //绑定交换机与队列
        channel.queueBind(queueName,EXCHANGE_NAME,"");
        System.out.println("等待接收消息,把接收到的消息打印在屏幕上");

        channel.basicConsume(queueName,true,consumer);
    }
}
