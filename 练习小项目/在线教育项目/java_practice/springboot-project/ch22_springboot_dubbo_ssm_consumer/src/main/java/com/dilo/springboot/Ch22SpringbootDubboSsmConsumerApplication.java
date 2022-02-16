package com.dilo.springboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfig    //开启dubbo配置
public class Ch22SpringbootDubboSsmConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch22SpringbootDubboSsmConsumerApplication.class, args);
    }

}
