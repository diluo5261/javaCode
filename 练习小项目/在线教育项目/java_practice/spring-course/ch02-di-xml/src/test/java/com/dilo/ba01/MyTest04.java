package com.dilo.ba01;

import com.dilo.ba04.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest04 {

    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ba04/applicationContext.xml");

        Student student = (Student) classPathXmlApplicationContext.getBean("Student");

        System.out.println(student);

    }


}
