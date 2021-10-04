package com.dilo;
import com.dilo.ba01.SomeService;
import com.dilo.ba01.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {

    @Test
    public void Test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        从容器中获取目标对象

        SomeService someService = (SomeService) context.getBean("someService");

        //通过代理的对象执行方法,实现目标方法执行时,增强了功能
        someService.doSome("lisi",56);

    }
}
