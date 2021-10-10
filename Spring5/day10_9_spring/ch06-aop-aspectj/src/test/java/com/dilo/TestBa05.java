package com.dilo;

import com.dilo.ba05.SomeService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBa05 {

    @Test
    public void test(){

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SomeService someService = (SomeService) context.getBean("someService");

        String str = someService.doOther("李四",9);

        System.out.println("测试方法返回值"+str+(10/0));
    }
}
