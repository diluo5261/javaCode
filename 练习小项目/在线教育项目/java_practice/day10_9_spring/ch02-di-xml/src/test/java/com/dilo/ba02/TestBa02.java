package com.dilo.ba02;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa02 {

    @Test
    public void test02(){
        //1.读取配置文件,获取spring 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ba02/applicationContext.xml");

        //2.根据对象的 id 需要的对象
        Student student = (Student) context.getBean("student");

        System.out.println(student);
    }
}
