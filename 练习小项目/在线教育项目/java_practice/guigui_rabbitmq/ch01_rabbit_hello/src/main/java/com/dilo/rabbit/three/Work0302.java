package com.dilo.rabbit.three;

import com.dilo.rabbit.one.Producer;
import com.dilo.rabbit.utils.RabbitUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//消息在手动应答时是不丢失,放回队列种重新消费
public class Work0302 {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtil.getChannel();
        System.out.println("C2等待接收消息处理时间较长");

        //声明接收消息的表达式
        DeliverCallback deliverCallback = (consumerTag, message)->{
            //沉睡一秒钟
            try {
                Thread.sleep(2000);
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
        //int prefetchCount = 1;
        int prefetchCount = 5;
        channel.basicQos(prefetchCount);

        //采用手动应答 
        boolean autoAck = false;
        channel.basicConsume(Producer.QUEUE_NAME,autoAck,deliverCallback,callback);
    }

}
