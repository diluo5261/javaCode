package com.dilo.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//springboot 项目启动入口类
@SpringBootApplication      //开启springboot 配置
public class Ch002SpringbootMvcApplication {

    //springboot 项目代码必须放到 Application 类所在的统计目录或者下级目录

    public static void main(String[] args) {
        SpringApplication.run(Ch002SpringbootMvcApplication.class, args);
    }

}
