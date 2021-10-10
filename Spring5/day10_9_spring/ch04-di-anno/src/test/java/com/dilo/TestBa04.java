package com.dilo;

import com.dilo.ba04.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa04 {

    @Test
    public void test(){
        //读取配置文件, 获取spring 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ba01/applicationContext.xml");

        //根据id 从spring 容器中获取对象
        Student student = (Student) context.getBean("student04");


        System.out.println(student);
    }
}
