package com.dilo.service.impl;

import com.dilo.service.SomeService;

public class SomeServiceImpl implements SomeService {

    public SomeServiceImpl() {
        System.out.println("someService的无参构造方法!");
    }

    @Override
    public void doSome() {
        System.out.println("someService 的重写doSome方法");
    }
}
