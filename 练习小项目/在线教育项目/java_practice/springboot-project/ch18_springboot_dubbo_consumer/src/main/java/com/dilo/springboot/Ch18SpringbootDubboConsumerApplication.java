package com.dilo.springboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   //开启spring配置
@EnableDubboConfig      //开启dubbo配置
public class Ch18SpringbootDubboConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch18SpringbootDubboConsumerApplication.class, args);
    }

}
