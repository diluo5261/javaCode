package com.dilo.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("bootDirectExchange");
    }

    //配置一个队列
    @Bean
    public Queue directQueue(){
        return new Queue("bootDirectQueue");
    }


    /**
     * 配置一个队列和交换机的绑定
     * @param directQueue  需要绑定的队列的对象,参数名要于某个@Bean的方法名完成一致,这样就会自动进行注入
     * @param directExchange 需要绑定的交换机的对象,参数名必须要与某个@Bean方法名完成相同,这样机会自动进行注入
     * @return
     */
    //配置一个队列和交换机的绑定
    @Bean
    public Binding directBinding(Queue directQueue, DirectExchange directExchange){
        //完成绑定
        return BindingBuilder.bind(directQueue).to(directExchange).with("bootDirectRouting");

    }
}
