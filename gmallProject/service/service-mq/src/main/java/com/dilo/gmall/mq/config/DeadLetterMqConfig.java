package com.dilo.gmall.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration//配置类
public class DeadLetterMqConfig {
    // 声明一些变量

    public static final String exchange_dead = "exchange.dead";
    public static final String routing_dead_1 = "routing.dead.1";
    public static final String routing_dead_2 = "routing.dead.2";
    public static final String queue_dead_1 = "queue.dead.1";
    public static final String queue_dead_2 = "queue.dead.2";

    //创建一个队列
    @Bean
    public Queue queue1(){
        HashMap<String, Object> map = new HashMap<>();
        // 参数绑定 此处的key 固定值，不能随意写
        map.put("x-dead-letter-exchange",exchange_dead);
        map.put("x-dead-letter-routing-key",routing_dead_2);
        map.put("x-message-ttl",10*1000);
        return new Queue(queue_dead_1,true,false,false,map);
    }

    //声明一个交换机
    @Bean
    public DirectExchange exchange(){

        return new DirectExchange(exchange_dead,true,false);
    }

    //设置绑定关系
    @Bean
    public Binding binding1(){
        //将队列1通过routing_dead_1 key 绑定到exchange_dead交换机上
        return BindingBuilder.bind(queue1()).to(exchange()).with(routing_dead_1);
    }

    //创建一个队列
    @Bean
    public Queue queue2(){

        return new Queue(queue_dead_2,true,false,false);
    }

    //设置绑定关系
    @Bean
    public Binding binding2(){
        //将队列1通过routing_dead_1 key 绑定到exchange_dead交换机上
        return BindingBuilder.bind(queue2()).to(exchange()).with(routing_dead_2);
    }


}
