package com.dilo.springboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //开启spring注解配置
@EnableDubboConfig
public class Ch18SpringbootDubboProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch18SpringbootDubboProvideApplication.class, args);
    }

}
