package com.dilo;

/**
 * 实现接口 Runnable 来开发线程
 */
public class ThreadImplementDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(new Dog(),"h5");
        thread.start();

    }
}

class Dog implements  Runnable{

    @Override
    public void run() {
        while(true){
            System.out.println("hi!!"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
