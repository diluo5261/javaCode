package com.dilo.springboot;

import com.dilo.springboot.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Ch26SpringbootJava1Application {
    /*
    springboot程序启动后,返回值时 ConfigurableApplicationContext,它也是一个spring容器
    它其实相当于原来spring容易中启动容器 classpathXmlApplicationContest
     */

    public static void main(String[] args) {
        //获取springboot容器
        ConfigurableApplicationContext context = SpringApplication.run(Ch26SpringbootJava1Application.class, args);

        //从spring容器中获取指定bean对象
        StudentService studentService = (StudentService) context.getBean("studentServiceImpl");

        //调用业务方法
        String res = studentService.sayHello();
        System.out.println(res);
    }

}
