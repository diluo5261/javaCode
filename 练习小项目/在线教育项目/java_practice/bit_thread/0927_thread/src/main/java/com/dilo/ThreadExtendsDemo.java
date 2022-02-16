package com.dilo;

public class ThreadExtendsDemo {
    public static void main(String[] args) {
        Thread thread = new Cat();
        thread.start();//启动线程
    }
}


//1.当一个类继承了 Thread 类, 该类就可以当作线程使用了
//2. 我们会重写run 方法,写上自己的业务代码
//3. run Thread 类 实现类 Runnable 接口的方法
class Cat extends Thread{

    @Override
    public void run() {
        //重写run方法,写上自己的业务逻辑
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("喵~~");
        }
    }
}
