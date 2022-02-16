package com.dilo.day10_11.ba01_thteadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {

    static class Worker extends Thread{

        private BlockingQueue<Runnable> queue;
        public Worker(BlockingQueue<Runnable> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
           while(true){
               //工作线程具体内容
               //从队列取数据
               try {
                   Runnable command = queue.take();
                   command.run();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
        }
    }

    static class ThreadPool{
        private static final int MAX_WORKER_COUNTER = 10;
        //包含一个阻塞队列,用来组织任务
        private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

        //list来存放当前的线程
        private List<Thread> workers = new ArrayList<>();

        //通过这个方法,把任务加入到线程池当红
        //submit 不光可以把任务放到阻塞队列当中,同时也可以负责创建线程
        public void submit(Runnable command) throws InterruptedException {

            if (workers.size() < MAX_WORKER_COUNTER){
                //如果=当前工作线程不足线程的上限,就创建线程对象
                //工作的线程就专门创建一个类来完成
                //worker内部要能够取到队列的内容,
                Worker worker = new Worker(queue);
                worker.start();
                workers.add(worker);

            }
            queue.put(command);

        }

    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        int i =0;
        for (;i<10;i++){
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                }
            });
        }
    }

}
