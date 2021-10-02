package com.dilo;

import com.dilo.ba04.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest04 {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext04.xml");

        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
