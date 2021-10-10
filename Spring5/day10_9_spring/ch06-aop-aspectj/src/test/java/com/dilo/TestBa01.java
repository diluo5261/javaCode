package com.dilo;

import com.dilo.ba01.SomeService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa01 {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SomeService someService = (SomeService) context.getBean("someService");

        someService.doSome("张三",16);
    }
}
