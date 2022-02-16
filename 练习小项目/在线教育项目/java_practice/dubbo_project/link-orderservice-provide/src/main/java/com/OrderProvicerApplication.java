package com;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class OrderProvicerApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("orderservice-provider.xml");
        //容器的启动方法
        context.start();

        //让提供者一直运行
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
