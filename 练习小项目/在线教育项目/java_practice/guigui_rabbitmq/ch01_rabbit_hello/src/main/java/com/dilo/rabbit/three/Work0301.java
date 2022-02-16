package com.dilo.rabbit.three;

import com.dilo.rabbit.one.Producer;
import com.dilo.rabbit.utils.RabbitUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消息在手动应答时是不丢失,放回队列种重新消费
public class Work0301 {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtil.getChannel();
        System.out.println("C1等待接收消息处理时间较短");

        //声明接收消息的表达式
        DeliverCallback deliverCallback = (consumerTag, message)->{
            //沉睡一秒钟
            try {
                Thread.sleep(1000);
                System.out.println("接收到消息"+new String(message.getBody()));


                //手动应答
                /*
                    1.消息的标记 tag
                    2.是否批量应答 false : 不批量应答
                 */
                channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        //取消消息时的对调
        CancelCallback callback = var1->{
            System.out.println("消息被中段");
        };

        //采用不公平分发
        int prefetchCount = 2;
        channel.basicQos(prefetchCount);

        //采用手动应答
        boolean autoAck = false;
        //channel.basicConsume(Producer.QUEUE_NAME,autoAck,deliverCallback,callback);
        channel.basicConsume(Producer.QUEUE_NAME,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //获取消息的编号,我们需要根据消息的编号来确认消息
                long tag = envelope.getDeliveryTag();
                //手动确认消息,确认以后次奥是当前消息已经成功处理了,需要从队列中移除掉
                //这个方法应该在当前消息的处理程序中全部完成以后执行
                //参数1:为消息的序号
                //参数2:为是否确认多个,如果为true,则表示区域总确认小于等于当前编号的所有消息,false就是单个确认值,确认当前消息
                channel.basicAck(tag,false);
            }
        });
    }

}
