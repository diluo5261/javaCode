package com.dilo.day10_11.ba01_thteadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //使用以下标准库中的线程池,先创建一个线程池的实例
        ExecutorService service = Executors.newFixedThreadPool(10);

        //给这个实例里面加入一些任务
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");

            }
        });

    }
}
