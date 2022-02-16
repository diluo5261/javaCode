package com.dilo;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

public class Ticket12306 implements Runnable{
    private int ticket = 10;
    private InterProcessMutex lock;

    public Ticket12306(){
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.169.128:2181")
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(60 * 1000)
                .namespace("dilo").build();

        client.start();
        lock = new InterProcessMutex(client,"lock");
    }

    @Override
    public void run() {
        while(true){
            //获取锁
            //参数1:等待时间,参数2:时间单位
            try {
                lock.acquire(3, TimeUnit.SECONDS);
                if (ticket > 0){
                    System.out.println(Thread.currentThread()+":"+ticket);
                    ticket--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
