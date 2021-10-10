package com.dilo.ba05;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa05 {
    @Test
    public void test(){
        //读取配置文件,获取spring 容器对象

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ba05/applicationContext.xml");

        //通过对象 id 获取对象
        Student student = (Student) context.getBean("student");

        System.out.println(student);
    }
}
