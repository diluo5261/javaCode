package com.dilo.aop.impl;

import com.dilo.aop.TargetInterface;
import org.springframework.stereotype.Component;

@Component
public class Target implements TargetInterface {

    @Override
    public int method(String name,int age) {
        System.out.println(name+age+100);
        age=age+100;
        System.out.println("target running...");
        return age;
    }
}
