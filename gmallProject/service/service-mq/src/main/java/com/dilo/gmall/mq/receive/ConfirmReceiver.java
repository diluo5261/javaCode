package com.dilo.gmall.mq.receive;

import com.dilo.gmall.mq.config.DeadLetterMqConfig;
import com.dilo.gmall.mq.config.DelayedMqConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ConfirmReceiver {
    //监听获取消息,设置绑定消息

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue.confirm",durable = "true",autoDelete = "fasle"),
            exchange = @Exchange(value = "exchange.confirm"),
            key = {"routing.confirm"}
    ))
    public void getMsg(String msg, Message message, Channel channel){
        try {
            System.out.println("接收到的消息:"+msg);
            //手动确认
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            //false标识一个一个的确认,true标识批量确认
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = DeadLetterMqConfig.queue_dead_2)
    public void getMag1(String msg, Message message, Channel channel){
        try {
            System.out.println("接收到的消息"+msg);
            System.out.println("接收到的时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //监听队列消息,基于插件
    @RabbitListener(queues = DelayedMqConfig.queue_delay_1)
    public void getMag2(String msg, Message message, Channel channel){
        try {
            System.out.println("接收到的消息"+msg);
            System.out.println("接收到的时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
