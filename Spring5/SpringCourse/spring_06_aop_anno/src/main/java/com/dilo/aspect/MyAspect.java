package com.dilo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {

    @Before("execution(public void *(..))")
    public void before(JoinPoint joinPoint){
        System.out.println("连接点的方法定义"+joinPoint.getArgs().length);
        System.out.println("连接点方法的参数"+joinPoint.getSignature());
        System.out.println("前置通知");
    }

    @AfterReturning(value ="myPoint()",returning = "result")
    public void afterReturning(Object result){
        result =result+"a";
        System.out.println(result+"a");
        System.out.println("后置通知");
    }

    @Around("myPoint()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("环绕通知,在目标方法执行前");
        joinPoint.proceed();
        System.out.println("环绕通知,在目标方法执行后");
    }

    @Pointcut("execution(public * *(..))")
    public void myPoint(){};
}
