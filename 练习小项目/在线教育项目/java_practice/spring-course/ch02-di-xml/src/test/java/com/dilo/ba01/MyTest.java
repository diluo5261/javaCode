package com.dilo.ba01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test01(){
        //创建容器的对象
        ApplicationContext context = new ClassPathXmlApplicationContext("ba02/applicationContext.xml");

        //通过容器获取student对象
        Student student = (Student) context.getBean("Student");
        System.out.println(student);



    }
}
