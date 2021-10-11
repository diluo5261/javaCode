package com.dilo;

public class ThreadPrastic2 {
    public static void main(String[] args) {
//        Thread t1 = new SellTicket01();
//        Thread t2 = new SellTicket01();
//        Thread t3 = new SellTicket01();

        SellTicket02 s2 = new SellTicket02();
        Thread t1 = new Thread(s2);
        Thread t2 = new Thread(s2);
        Thread t3 = new Thread(s2);

        //会出现票数超卖现象
        t1.start();
        t2.start();
        t3.start();

    }
}

class SellTicket01 extends Thread{
    private static int tickietNum = 100;


    @Override
    public void run() {
        while(true){
            if (tickietNum <= 0){
                System.out.println("售票结束");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            tickietNum--;
            System.out.println("窗口"+Thread.currentThread().getName()+"售出了一张票,剩余票数"+(--tickietNum));

        }
    }
}

class SellTicket02 extends Thread{
    private static int tickietNum = 100;


    @Override
    public void run() {
        while(true){
            if (tickietNum <= 0){
                System.out.println("售票结束");
                break;
            }

            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
//            tickietNum--;
            System.out.println("窗口"+Thread.currentThread().getName()+"售出了一张票,剩余票数"+(--tickietNum));

        }
    }
}
