package com.dilo.ba02;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {

    @AfterReturning(value = "execution(* *..SomeServiceImpl.doOther(..))", returning = "res")
    public void myAfterReturning(Object res){
        System.out.println("后置通知,在目标方法执行后执行!");

        System.out.println("返回值是"+res);
    }



}
