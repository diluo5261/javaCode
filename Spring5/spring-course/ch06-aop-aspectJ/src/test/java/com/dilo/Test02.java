package com.dilo;
import com.dilo.ba02.SomeService;
import com.dilo.ba02.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test02 {

    @Test
    public void Test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        从容器中获取目标对象

        SomeService someService = (SomeService) context.getBean("someService");

        //通过代理的对象执行方法,实现目标方法执行时,增强了功能
        Student student = someService.doOther2("lisi",56);
        System.out.println(student);

    }
}
