package com.dilo.day10_13;

import java.util.concurrent.locks.ReentrantLock;

public class Retun {
    static class Count{
        public int counter = 0;

        public ReentrantLock locker = new ReentrantLock();

        //ReentrantLock 把加锁的解锁操着拆分开了
//        这种风格的代码是常见的写法,很多编程语言都是这个样子的
        public void increase(){
            locker.lock();
            counter++;
            locker.unlock();
        }
    }

    public static void main(String[] args) {

    }
}
