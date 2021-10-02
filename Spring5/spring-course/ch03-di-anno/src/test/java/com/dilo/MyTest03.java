package com.dilo;

import com.dilo.ba03.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest03 {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext03.xml");

        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
