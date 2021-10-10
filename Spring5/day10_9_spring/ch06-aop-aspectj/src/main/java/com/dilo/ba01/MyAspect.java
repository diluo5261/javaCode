package com.dilo.ba01;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

    @Before("execution(* doSome(..))")
    public void myBefore(){
        System.out.println("前置通知,在目标方法执行前执行!");
    }

}
