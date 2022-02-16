package com.dilo.util;

import java.util.Date;

public class ServiceTools {

    public static void doLog(){
        System.out.println("程序运行时间"+new Date());
    }

    public static void doTrans(){
        System.out.println("程序完成,提交事务");
    }
}
