package com.dilo.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//必须实现InvocationHandler接口,完成代理类要做的功能(1.调用目标方法,2.目标增强)
public class MyShellHandler implements InvocationHandler {
    private Object target ;

    public MyShellHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //执行目标方法
        Object res = method.invoke(target,args);

        if (res != null){
            Float price = (Float) res;
            price += 25;
            res = price;
        }
        return res;
    }
}
