package com.dilo.day10_13;

import java.util.concurrent.Semaphore;

public class 信号量 {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(4);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //先尝试申请资源
                try {
                    semaphore.acquire();
                    System.out.println("申请资源成功");
                    //申请后睡眠1s
                    Thread.sleep(1000);
                    //释放资源
                    semaphore.release();
                    System.out.println("释放资源成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ;

            }
        };

        //创建 20 个线程
        //让这20 个线程分别去尝试申请资源
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(runnable);
            thread.start();

        }

    }
}
