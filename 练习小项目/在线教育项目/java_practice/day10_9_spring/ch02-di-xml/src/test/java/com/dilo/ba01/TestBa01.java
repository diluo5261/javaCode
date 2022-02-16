package com.dilo.ba01;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa01 {

    @Test
    public void test1(){
        //读取配置文件,获取spring 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ba01/applicationContext.xml");

        //根据对象 id 获取需要的对象
        Student student = (Student) context.getBean("student");

        System.out.println(student);
    }
}
