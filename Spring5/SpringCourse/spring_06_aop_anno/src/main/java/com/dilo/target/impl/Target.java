package com.dilo.target.impl;

import com.dilo.target.ITarget;
import org.springframework.stereotype.Component;

@Component
public class Target implements ITarget {
    @Override
    public void method1() {
        System.out.println("target method1 is running ...");
    }

    @Override
    public String method2(String name, int age) {
        System.out.println("name:" + name+" age:"+age);
        System.out.println("target method2 is running ...");
        return name+age+100;
    }
}
