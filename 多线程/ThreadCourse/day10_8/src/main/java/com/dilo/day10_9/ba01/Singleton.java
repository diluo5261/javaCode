package com.dilo.day10_9.ba01;

public class Singleton {
    //使用 volatile 保证内存可见性
    private static volatile Singleton instance = null;

    //私有化构造器,防止调用构造器进行创建实例
    private Singleton(){};

    public static Singleton getInstance(){
        if (instance == null){
            synchronized(Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
