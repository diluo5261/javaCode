package com.dilo.ba03;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa03 {
    @Test
    public void test(){

        //1.读取配置文件获取spring 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ba03/applicationContext.xml");

        //2.通过对象的 id ,获取对象

        Student student = (Student) context.getBean("studentIndex2");

        System.out.println(student);




    }
}
