package com.dilo.rabbit.four;

import com.dilo.rabbit.utils.RabbitUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

/*
    发布确认模式
    使用的时间,比较那种确认比较好

 */
public class ConfirmMessage {

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        //publicMessageIndividually();   //556ms

        //publicMessageBatch();  //94ms
        publicMessageAsync();   //39ms
    }

    public static void publicMessageIndividually() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitUtil.getChannel();
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();

        //开始时间
        long begin = System.currentTimeMillis();

        //批量发消息
        for (int i = 0; i < 1000; i++) {
            String msg = "第"+i+"条数据";
            channel.basicPublish("",queueName,null,msg.getBytes());

            //单个消息就马上确认发布
            boolean b = channel.waitForConfirms();

        }

        long end = System.currentTimeMillis();
        System.out.println("发布一千条单独确认消息,耗时"+(end - begin));

    }

    public static void publicMessageBatch() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitUtil.getChannel();
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();

        //开始时间
        long begin = System.currentTimeMillis();

        //批量确认消息大小
        int batchSize = 100;

        //批量发消息
        for (int i = 0; i < 1000; i++) {
            String msg = "第"+i+"条数据";
            channel.basicPublish("",queueName,null,msg.getBytes());

            //判断达到了100条消息的时候,批量确认一次
            if (i%batchSize == 0){
                channel.waitForConfirms();
            }

        }

        long end = System.currentTimeMillis();
        System.out.println("发布一千条批量确认消息,耗时"+(end - begin));

    }

    //异步发布确认
    public static void publicMessageAsync() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitUtil.getChannel();
        String queueName = UUID.randomUUID().toString();

        channel.queueDeclare(queueName,true,false,false,null);

        //开启发布确认
        channel.confirmSelect();

        //开始时间
        long begin = System.currentTimeMillis();

        /*
        线程安全有序的一个哈希表,适用于高并发的情况下
        1. 轻松的将序号与消息进行关联
        2.轻松批量删除条目,只要给到序号
        3.支持高并发(多线程)
         */

        ConcurrentSkipListMap<Long,String> outstandingConfirms = new ConcurrentSkipListMap<>();


        //确认消息成功,回调函数
        ConfirmCallback ackCallback = (deliveryTag,multiple)->{
            //System.out.println("确认消息"+deliveryTag);
            //2.删除已经确认的消息,剩下的就是为确认的消息

            if (multiple){
                ConcurrentNavigableMap<Long, String> navigableMap = outstandingConfirms.headMap(deliveryTag);
                navigableMap.clear();
            }else{
                outstandingConfirms.remove(deliveryTag);
            }

        };
        //确认消息失败回调函数
        /*
            1.消息的标记
            2.是否为批量确认
         */
        ConfirmCallback nackCallback = (deliveryTag,multiple)->{
            System.out.println("未确认消息");

        };


        //准备消息的监听器,监听哪些消息发送成功,哪些消息失败了
        //channel.addConfirmListener(ackCallback,nackCallback);
        channel.addConfirmListener(new ConfirmListener() {
            //参数1 为被确认的消息的编号,从 1 开始自动递增,用于标记当前是第几个消息
            //参数2 为当前消息是否同时确认多个,
            //如果参数2 为true,则表示本次确认同时确认了多条消息,消息等于当前参数1(消息编号)的所有消息
            //如果被确认为false,则表示值确认了多个当前编号的消息
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                //消息确认以后的回调方法
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                //消息没有确认的回调方法,如果这个方法被执行,说明这个消息没有被确认,需要进行补发
            }
        });

        for (int i = 0; i < 1000; i++) {
            String msg = "第"+i+"条数据";
            channel.basicPublish("",queueName,null,msg.getBytes());

            //1.记录此处下所有的消息
            outstandingConfirms.put(channel.getNextPublishSeqNo(),msg);

        }

        long end = System.currentTimeMillis();
        System.out.println("发布一千条异步确认消息,耗时"+(end - begin));

    }


}
