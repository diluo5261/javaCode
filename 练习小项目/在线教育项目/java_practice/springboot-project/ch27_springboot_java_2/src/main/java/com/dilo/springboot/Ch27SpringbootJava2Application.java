package com.dilo.springboot;

import com.dilo.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ch27SpringbootJava2Application  implements CommandLineRunner {
    @Autowired
    private StudentService studentService;

    public static void main(String[] args) {
        //springboot启动程序,会初始化spring容器
        SpringApplication.run(Ch27SpringbootJava2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //调用业务方法
        String res = studentService.sayHello("你好中国!!");
        System.out.println(res);

    }

    /*
    1.让类实现一个CommandRunner接口
    2.重写 run方法
     */
}
