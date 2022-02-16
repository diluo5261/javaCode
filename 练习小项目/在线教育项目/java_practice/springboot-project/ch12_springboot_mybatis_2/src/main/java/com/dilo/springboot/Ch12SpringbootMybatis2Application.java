package com.dilo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan(basePackages = "com.dilo.springboot.mapper")        //扫描注解的第二种方式,basepackage可以省略
public class Ch12SpringbootMybatis2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch12SpringbootMybatis2Application.class, args);
    }

}
