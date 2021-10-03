package com.dilo.handler;

import com.dilo.util.ServiceTools;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyIncationHandler implements InvocationHandler {
    private Object target;

    public MyIncationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //通过代理对象执行方法时,会调用invoke()

        String methodName = method.getName();

        System.out.println("执行MyIncationHandler中的invoke()");
        Object res = null;

        if ("doSome".equals(methodName)){
            ServiceTools.doLog();
            //执行目标类的方法,通过Method类实现
            res = method.invoke(target,args);

            ServiceTools.doTrans();
        }else{
            //执行目标类的方法,通过Method类实现
            res = method.invoke(target,args);
        }

        return res;
    }
}
