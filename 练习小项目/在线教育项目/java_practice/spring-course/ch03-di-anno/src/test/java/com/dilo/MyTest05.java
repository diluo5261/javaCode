package com.dilo;

import com.dilo.ba05.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest05 {

    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext05.xml");

        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }
}
