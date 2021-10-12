package com.dilo.day10_9.ba02;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class 定时器实现 {

    static class Task implements Comparable<Task>{
        //command 表示这个任务是什么
        private Runnable command;
        //time 表示这个任务啥时候到时间,这里的time 使用 ms 级时间戳来表示
        private long time;

        //time 为时间差,
        //this.time来保存一个绝对的时间
        public Task(Runnable command, long time) {
            this.command = command;
            this.time = System.currentTimeMillis()+ time;
        }

        public void run(){
            command.run();
        }

        @Override
        public int compareTo(Task o) {
            return (int)(this.time - o.time);
        }
    }

    static class Timer{
        //使用带优先级版本的阻塞队列来组织这些任务
        private PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

        //使用这个locker 对象,解决忙等问题
        Object locker = new Object();

        public void schedule(Runnable command,long delay){
            Task task = new Task(command,delay);
            queue.put(task);

            //每次插入新的任务都要唤醒扫描线程,让扫描线程
            synchronized(locker){
                locker.notify();
            }
        }

        public Timer() {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    while(true){
                        try {
                            Task task = queue.take();
                            long curTime = System.currentTimeMillis();

                            if(task.time > curTime){
                                //时间还没到,暂时不执行
//前面的take 操作会把队首元素给删除掉,但是此时队首元素还没有执行,不能删除,于是需要重新插回队列
                                queue.put(task);

                                //根据时间插,来进行等待
                                synchronized(locker){
                                    locker.wait(task.time - curTime);
                                }
                            }else{
                                //时间到了
                                task.run();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                            //如果出现了interrupt方法,就退出循环
                            break;
                        }
                    }
                }
            };
            thread.start();
        }
    }

    public static void main(String[] args) {
        System.out.println("程序启动!");
        Timer timer = new Timer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        },3000);
    }
}
