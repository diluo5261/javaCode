package com.dilo.ba03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {


    @Around("execution(* *..doOther(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("环绕通知,执行在目标方法前:");

        Object result = pjp.proceed();

        System.out.println("环绕通知,执行在目标方法之后:");
        System.out.println("环绕通知返回值"+result);

        return result+"4555454";




    }


}
