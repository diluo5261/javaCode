package com.dilo;

import com.dilo.ba01.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa01 {

    @Test
    public void test(){
        //读取配置文件,获取spring 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ba01/applicationContext.xml");


        //根据 对象 id 获取对象
        Student student = (Student) context.getBean("student");

        System.out.println(student);
    }
}
