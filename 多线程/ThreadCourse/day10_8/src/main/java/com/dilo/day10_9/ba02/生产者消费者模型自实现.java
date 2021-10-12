package com.dilo.day10_9.ba02;

public class 生产者消费者模型自实现 {

    static class BlockingQueue{
        private int[] items = new int[1000];
        private int head = 0;
        private int tail = 0;
        private int size = 0;
        private Object locker = new Object();

        //put插入队列
        public void put(int item) throws InterruptedException {
            synchronized(locker){
                //入队列,就把新元素给放到 tail 位置上
                //此处的条件最好写作while 而不是 if
                //如果是有多个线程阻塞等待的时候,万一同时唤醒了多个线程
                //就有可能出现,第一个线程放入元素之后,第二个线程想放,就又满了的情况
                //虽然当前 take 的代码中使用的 notify , 一次只唤醒一个等待的线程,用 if 也不算错
                //但是用 while 更稳健一些,保证 wait 被唤醒的时候能够在确认一次队列确实不满
                while(size == items.length){
                    //队列已经满了,就要阻塞队列
                    locker.wait();
                }

                items[tail] = item;
                tail++;

                if (tail >= items.length){
                    tail = 0;
                }
                size++;
                locker.notify();
            }
        }

        //take 出队列
        public int take() throws InterruptedException {
            int ret = 0;
            synchronized (locker) {
                while (size == 0){
                    //对于阻塞队列来说,如果队列为空,就进行阻塞
                    locker.wait();
                }

                ret = items[head];
                head++;

                if(head >= items.length){
                    head = 0;
                }
                size--;
                //当队列中成功取出元素,队列就不满了,
                //唤醒put中阻塞的队列
                locker.notify();
            }
            return ret;

        }
    }

    public static void main(String[] args) {
        final BlockingQueue queue = new BlockingQueue();

        //消费者线程
        Thread consumer = new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        int value = queue.take();
                        System.out.println("消费元素"+value);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        consumer.start();

        //生产者线程
        Thread producer = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("生产元素"+i);
                    try {
                        queue.put(i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        producer.start();

    }
}
