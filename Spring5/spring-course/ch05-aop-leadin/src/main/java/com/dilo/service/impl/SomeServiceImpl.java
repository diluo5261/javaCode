package com.dilo.service.impl;

import com.dilo.service.SomeService;

public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome() {
        System.out.println("doSome");
    }

    @Override
    public void doOther() {

        System.out.println("doOther");

    }
}
