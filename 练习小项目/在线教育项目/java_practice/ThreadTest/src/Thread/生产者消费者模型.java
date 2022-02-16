package Thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class 生产者消费者模型 {
    private static  BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        Thread consumer = new Thread(){
            @Override
            public void run() {
                while(true){
                    //读取元素
                    try {
                        Integer value = queue.take();
                        System.out.println("消费元素"+ value);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        consumer.start();

        Thread producer = new Thread(){
            @Override
            public void run() {

                //生产元素
                for (int i = 0; i < 1000; i++) {
                    System.out.println("生产元素"+ i);
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
