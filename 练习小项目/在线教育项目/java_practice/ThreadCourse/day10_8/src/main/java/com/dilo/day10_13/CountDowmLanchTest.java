package com.dilo.day10_13;

import java.util.concurrent.CountDownLatch;

public class CountDowmLanchTest {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(8);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("起跑"+ Thread.currentThread().getName());

                try {
                    //random 方法得到一个 [0,1)之间的浮点数,sleep的单位是ms
                    Thread.sleep((long) (Math.random() *10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                latch.countDown();
                System.out.println("撞线完成"+ Thread.currentThread().getName());
            }
        };

        for (int i = 0; i < 8; i++) {
            Thread t = new Thread(runnable," "+i);
            t.start();
        }

        latch.await();
        System.out.println("比赛结束");
    }
}
