package com.dilo;

import com.dilo.ba02.SomeService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa02 {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SomeService someService = (SomeService) context.getBean("someService");

        someService.doOther("tom",56);
        someService.doSome("tom",58);
    }
}
