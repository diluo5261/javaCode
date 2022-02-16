package com.dilo.spring.test;

import com.dilo.spring.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    @Test
    public void testAdd(){
        //1. 加载spring配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean.xml");


        //2. 获取配置创建的对此
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
    }
}
