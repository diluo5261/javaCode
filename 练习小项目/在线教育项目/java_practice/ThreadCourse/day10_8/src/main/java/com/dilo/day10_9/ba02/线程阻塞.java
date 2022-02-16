package com.dilo.day10_9.ba02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class 线程阻塞 {
    public static void main(String[] args) throws InterruptedException {
        //内部是基于链表实现的
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        //插入元素,put带有阻塞功能, offer 不带有阻塞功能
        queue.put("hello");

        //取出元素
        String take = queue.take();
        System.out.println(take);


    }
}
