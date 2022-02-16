package Thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class 阻塞序列 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        //插入元素
        queue.put(15);

        //读取元素
        Integer take = queue.take();

        System.out.println(take);


    }
}
