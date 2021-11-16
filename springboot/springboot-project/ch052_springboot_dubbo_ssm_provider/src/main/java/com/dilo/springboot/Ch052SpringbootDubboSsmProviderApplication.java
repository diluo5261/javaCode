package com.dilo.springboot;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.dilo.springboot.mapper")
@EnableDubboConfig      //开启dubbo配置
public class Ch052SpringbootDubboSsmProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch052SpringbootDubboSsmProviderApplication.class, args);
    }

}
