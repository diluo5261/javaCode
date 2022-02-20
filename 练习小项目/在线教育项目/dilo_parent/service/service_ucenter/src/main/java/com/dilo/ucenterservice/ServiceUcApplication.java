package com.dilo.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.dilo"})
@SpringBootApplication
@MapperScan("com.dilo.ucenterservice.mapper")
public class ServiceUcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUcApplication.class, args);
    }

}
