package com.dilo.day10_11.ba01_thteadPool;

public class ThreadTEst {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName());

            }
        },"a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName());
            }
        },"b");
        Thread c = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName());
            }
        },"c");


        c.start();
        b.join();

        a.start();

        b.start();
        a.join();



    }
}
