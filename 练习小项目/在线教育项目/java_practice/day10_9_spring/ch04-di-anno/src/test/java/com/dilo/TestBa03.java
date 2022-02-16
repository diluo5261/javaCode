package com.dilo;

import com.dilo.ba03.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa03 {

    @Test
    public void test(){
        //读取配置文件,获取 spring 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ba01/applicationContext.xml");

        //根据对象 id 从 spring 容器中获取 对象
        Student student = (Student) context.getBean("myStudent");

        System.out.println(student);
    }
}
