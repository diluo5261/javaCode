package com.dilo;

public class ThreadDaemo {
    public static void main(String[] args) {
        MyDaemoThread myDaemoThread = new MyDaemoThread();

        //将子线程设置为守护线程
        myDaemoThread.setDaemon(true);

        myDaemoThread.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}

class MyDaemoThread extends Thread{
    @Override
    public void run() {

    }
}
