package com.dilo.day10_13;

public class ThreadCallable {
    static class  Result{
        public int sum;
        public Object locker = new Object();
    }

    public static void main(String[] args) throws InterruptedException {
        final Result result = new Result();

        Thread t = new Thread(){

            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i <= 100; i++) {
                    sum += i;
                }
                result.sum =sum;
                synchronized (result.locker ){
                    result.locker.notify();
                }
            }
        };

        t.start();

        synchronized (result.locker){

            result.locker.wait();
        }

        System.out.println(result.sum);

    }
}
