package com.dilo.ba04;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {

    @AfterThrowing(value = "execution(* *..SomeServiceImpl.doOther(..))",throwing = "ex")
    public void myAfterThrowing(Exception ex){

        System.out.println("异常通知,方法发生异常时,执行的方法"+ex.getMessage());


    }










}
