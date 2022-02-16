package com.dilo.ba05;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {

    @After("execution(* *..SomeServiceImpl.doOther(..))")
    public void myAfter(){
        System.out.println("执行最终通知,总是会被执行的代码");
    }










}
