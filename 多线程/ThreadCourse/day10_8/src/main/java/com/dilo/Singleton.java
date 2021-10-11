package com.dilo;

public class Singleton {
    private static SingleTest single = null;
    private  Singleton(){};

    public static SingleTest getInstance(){
        if (single ==null){
            synchronized(Singleton.class){
                if (single == null){
                    single = new SingleTest();
                }
            }
        }
        return single;
    }

}
