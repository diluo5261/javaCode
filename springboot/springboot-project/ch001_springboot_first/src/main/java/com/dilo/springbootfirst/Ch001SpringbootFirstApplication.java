package com.dilo.springbootfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//SpringBoot项目启动入口
//SpringBoot 核心注解,主要用于开启 spring 自动配置
@SpringBootApplication
public class Ch001SpringbootFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch001SpringbootFirstApplication.class, args);
    }

}
