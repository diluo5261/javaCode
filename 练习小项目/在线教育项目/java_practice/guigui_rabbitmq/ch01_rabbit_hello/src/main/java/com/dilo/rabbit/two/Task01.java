package com.dilo.rabbit.two;

import com.dilo.rabbit.one.Producer;
import com.dilo.rabbit.utils.RabbitUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Task01 {

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtil.getChannel();

        channel.queueDeclare(Producer.QUEUE_NAME,false,false,false,null);

        for (int i = 0; i < 10; i++) {
            String msg = "第"+i+"条消息!";


            channel.basicPublish("",Producer.QUEUE_NAME,null,msg.getBytes());
        }
        System.out.println("发送消息完成!");
        /*
        basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        参数：
            1. exchange：交换机名称。简单模式下交换机会使用默认的 ""
            2. routingKey：路由名称,key值是哪个,本次是队列的名称
            3. props：配置信息
            4. body：发送消息数据

         */

    }
}
