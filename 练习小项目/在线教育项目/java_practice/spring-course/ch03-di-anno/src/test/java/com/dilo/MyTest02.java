package com.dilo;

import com.dilo.ba02.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest02 {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext02.xml");

        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
