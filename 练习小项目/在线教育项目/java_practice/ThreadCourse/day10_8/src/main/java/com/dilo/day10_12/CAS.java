package com.dilo.day10_12;

import java.util.concurrent.atomic.AtomicInteger;

public class CAS {
    public static void main(String[] args) {
//        使用原子类
        AtomicInteger num = new AtomicInteger(10);
        //这个操作相当于++,
        num.getAndIncrement();
        System.out.println(num);

        //这个操作相当于 后置++
        num.incrementAndGet();

    }
}
