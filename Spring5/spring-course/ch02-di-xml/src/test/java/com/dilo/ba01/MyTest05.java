package com.dilo.ba01;

import com.dilo.ba05.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest05 {

    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ba05/applicationContext.xml");

        Student student = (Student) classPathXmlApplicationContext.getBean("Student");

        System.out.println(student);

    }


}
