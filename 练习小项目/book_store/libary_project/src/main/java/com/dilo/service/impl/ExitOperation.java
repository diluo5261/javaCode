package com.dilo.service.impl;

import com.dilo.service.IOperation;

public class ExitOperation implements IOperation {

    @Override
    public void work() {
        System.out.println("退出程序!");
        System.exit(0);

    }
}
