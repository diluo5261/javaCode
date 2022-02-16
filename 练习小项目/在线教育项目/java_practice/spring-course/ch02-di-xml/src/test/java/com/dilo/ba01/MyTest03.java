package com.dilo.ba01;

import com.dilo.ba03.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest03 {

    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ba03/applicationContext.xml");

        Student student = (Student) classPathXmlApplicationContext.getBean("Student");

        System.out.println(student);

    }

    @Test
    public void test1(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ba03/applicationContext.xml");

        Student student = (Student) classPathXmlApplicationContext.getBean("student2");

        System.out.println(student);

    }
}
