package com.dilo;

import static org.junit.Assert.assertTrue;

import com.dilo.target.ITarget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppTest {

    @Autowired
    private ITarget target;

    @Test
    public void test01(){
        target.method1();
    }

    @Test
    public void test02(){
        String res = target.method2("张三",55);
        System.out.println(res);
    }



}
