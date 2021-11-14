package com.dilo.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.dilo.springboot.mapper")
@EnableTransactionManagement  //开启事务（可选项，可加可不加）
public class Ch14SpringbootTranscationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch14SpringbootTranscationApplication.class, args);
    }

}
