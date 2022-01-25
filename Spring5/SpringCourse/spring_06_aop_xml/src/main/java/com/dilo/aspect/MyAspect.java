package com.dilo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {

    public void before(JoinPoint joinPoint){
        System.out.println("连接点方法定义"+joinPoint.getSignature());
        System.out.println("连接点方法的参数个数"+joinPoint.getArgs().length);
        System.out.println("前置通知");
    }

    public void after(Object result){
        System.out.println(""+result+666);
        System.out.println("后置通知");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知,在目标方法执行之前执行");
        joinPoint.proceed();//执行目标方法
        System.out.println("环绕通知,在目标方法后执行");

    }
}
