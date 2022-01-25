package com.dilo.aop.impl;

import com.dilo.aop.ITarget;

public class Target implements ITarget {
    @Override
    public void method1() {
        System.out.println("method1 is running...");
    }

    @Override
    public String method(String name, int age) {

        System.out.println("method2 is Running ...");
        System.out.println("name:"+name+" age:"+age);
        return name+age+100;
    }
}
