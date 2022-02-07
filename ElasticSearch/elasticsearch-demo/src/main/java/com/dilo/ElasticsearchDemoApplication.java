package com.dolo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.atguigu.dao")
@SpringBootApplication
public class ElasticsearchDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchDemoApplication.class,args);
    }
}
