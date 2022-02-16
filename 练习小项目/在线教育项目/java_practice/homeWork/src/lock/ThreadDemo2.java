package lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    share.increase();
                }
            }
        },"AA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            }
        },"BB").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    share.increase();
                }
            }
        },"CC").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    share.decr();
                }
            }
        },"DD").start();
    }
}

//创建资源类
class Share{
    private  int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void increase(){
        //上锁
        lock.lock();
        try{
            //判断
            while(number != 0){
                condition.await();
            }

            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "::"+number);

            //通知
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    
    //减一
    public void decr(){
        lock.lock();
        try{
            while(number != 1){
                condition.await();
            }
            
            number--;
            System.out.println(Thread.currentThread().getName() + "::"+number);
            condition.signalAll();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
