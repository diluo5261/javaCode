package com.dilo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dilo.springboot.mapper")
public class Ch13SpringbootMybatis3Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch13SpringbootMybatis3Application.class, args);
    }

}
