package com.dilo;

import com.dilo.handler.MyIncationHandler;
import com.dilo.service.SomeService;
import com.dilo.service.impl.SomeServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test1 {
    public static void main(String[] args) {
        //创建目标对象
        SomeService target = new SomeServiceImpl();
        //创建Invocation对象
        InvocationHandler handler = new MyIncationHandler(target);

        SomeService proxy = (SomeService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),handler);

        //通过代理执行方法,会创建handler中的invoke方法
        proxy.doSome();
        System.out.println("--------------------------");
        proxy.doOther();



    }
}
