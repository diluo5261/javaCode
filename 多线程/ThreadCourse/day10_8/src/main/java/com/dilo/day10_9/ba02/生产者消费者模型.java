package com.dilo.day10_9.ba02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class 生产者消费者模型 {
    private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        //一个线程作为消费者
        Thread consumer = new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        Integer value = queue.take();
                        System.out.println("消费元素"+value);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        consumer.start();

        Thread producer = new Thread(){
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    System.out.println("生产元素"+ i);
                    try {
                        queue.put(i);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        producer.start();

    }
}
