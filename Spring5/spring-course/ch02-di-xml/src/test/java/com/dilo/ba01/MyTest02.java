package com.dilo.ba01;

import com.dilo.ba02.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest02 {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("ba02/applicationContext.xml");

        Student student = (Student) context.getBean("Student");
        System.out.println(student);

    }
}
