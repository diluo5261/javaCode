package com.dilo.wxlogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.dilo"})
@SpringBootApplication
@MapperScan("com.dilo.wxlogin.mapper")
public class WxLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxLoginApplication.class, args);
    }

}
