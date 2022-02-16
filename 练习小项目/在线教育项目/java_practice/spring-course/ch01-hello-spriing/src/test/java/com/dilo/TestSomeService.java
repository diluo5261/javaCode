package com.dilo;

import com.dilo.service.SomeService;
import com.dilo.service.impl.SomeServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;


public class TestSomeService {

    @Test
    public void test02(){
        //指定spring容器创建的对象
        //1.指定spring配置文件的名称
        String config ="beans.xml";

        //2.创建表示spring容器的对象,ApplicationContext
        //ApplicationContext 就表示spring 容器,就可以通过这个容器获取对象了,
//ClassPathXmlApplicationContext(config):表示从类路径中加载spring的配置文件


        ApplicationContext context = new ClassPathXmlApplicationContext(config);

        //从容器中获取某个对象,你要调用对象的方法
        //getBean("配置文件中的bean的id值)
        SomeService someService = (SomeServiceImpl) context.getBean("someServiceImpl");

        //使用
        someService.doSome();
    }

    /*
        spring默认创建对象的同时 : 在创建spring 的容器时, 会创建配置文件中的所有对象,

        spring创建对象:默认调用的时无参的构造方法
     */


    /**
     * 获取容器中对象的数量, 和对象的名称
     */
    @Test
    public void test03(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //获取对象的数量
        int nums = context.getBeanDefinitionCount();
        System.out.println(nums);

        //获取对象的名称
        String[] names = context.getBeanDefinitionNames();
        System.out.println(names);
    }

    //获取一个非自定义类的对象
    @Test
    public void test04(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Date mydata = (Date) context.getBean("mydate");

        System.out.println(mydata);


    }
}
