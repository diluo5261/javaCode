package com.dilo;

import com.dilo.aop.ITarget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MyTest {

    @Autowired
    private ITarget target;

    @Test
    public void test1(){
        target.method1();
    }

    @Test
    public void test2(){
        target.method("张三",100);
    }
}
