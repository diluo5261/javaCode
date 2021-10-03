package com.dilo.ba01;

import com.dilo.ba06.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest06 {

    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ba06/total.xml");

        Student student = (Student) classPathXmlApplicationContext.getBean("Student");

        System.out.println(student);

    }


}
