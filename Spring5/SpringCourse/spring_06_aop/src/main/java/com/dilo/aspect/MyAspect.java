package com.dilo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Before("execution(public void com.dilo.aop.*.*(..))")
    public void before(JoinPoint joinPoint){
        System.out.println("连接点方法的定义"+joinPoint.getSignature());
        System.out.println("连接点方法参数个数"+joinPoint.getArgs().length);
        System.out.println("在目标方法之前启动.....");
    }

    @AfterReturning("execution(public void com.dilo.aop.*.*(..))")
    public void after(){
        System.out.println("后置通知...");
    }
}
