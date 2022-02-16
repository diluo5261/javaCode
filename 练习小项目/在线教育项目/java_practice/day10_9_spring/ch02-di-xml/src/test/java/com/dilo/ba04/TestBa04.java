package com.dilo.ba04;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa04 {
    @Test
    public void test(){
        //1.读取配置文件,创建spring对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ba04/applicationContext.xml");

        //2.根据对象 id 获取对象
        Student student = (Student) context.getBean("student");

        System.out.println(student);
    }
}
