package com.dilo;
import com.dilo.ba03.SomeService;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test03 {

    @Test
    public void Test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        从容器中获取目标对象

        SomeService someService = (SomeService) context.getBean("someService");

        String str = someService.doFirst("tom",15);
        System.out.println(str);





    }
}
