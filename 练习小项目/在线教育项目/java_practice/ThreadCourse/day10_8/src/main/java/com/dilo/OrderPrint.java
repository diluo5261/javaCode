package com.dilo;

public class OrderPrint{
    public static void main(String[] args) throws InterruptedException {
        Object lockA = new Object();
        Object lockB = new Object();
        Object lockC = new Object();

        Thread t1 = new Thread(new Print(lockC,lockA,"A"));
        Thread t2 = new Thread(new Print(lockA,lockB,"B"));
        Thread t3 = new Thread(new Print(lockB,lockC,"C"));

        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);
        t3.start();

    }



}

class Print implements Runnable{

    //上个线程的锁
    private Object preLocal;
    //本线程的锁
    private Object thisLocal;

    //打印的字符
    private String print;

    public Print(Object preLocal, Object thisLocal, String print) {
        this.preLocal = preLocal;
        this.thisLocal = thisLocal;
        this.print = print;
    }


    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            // 获取前一个线程的打印锁
            synchronized (preLocal) {
                // 获取本线程的打印锁
                synchronized (thisLocal) {
                    //打印字符
                    System.out.print(print);
                    // 通过本线程的打印锁唤醒后面的线程

                    thisLocal.notify();
                }

                if(i < 10 - 1){
                    try {
                        // 通过fontLock等待被唤醒
                        preLocal.wait();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}
