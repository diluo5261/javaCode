package com.dilo.springboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfig
public class Ch053SpringbootDubboSsmConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch053SpringbootDubboSsmConsumerApplication.class, args);
    }

}
