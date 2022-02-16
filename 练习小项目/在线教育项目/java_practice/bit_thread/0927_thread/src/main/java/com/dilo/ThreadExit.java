package com.dilo;

public class ThreadExit {
    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                int count =0;
                while(true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hello"+(++count));
                }
            }
        },"线程1").start();
    }
}
