package com.dilo.day10_13;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable  = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
               int sum = 0;
                for (int i = 0; i <= 100; i++) {
                    sum += i;
                }

                return sum;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread t = new Thread(task);
        t.start();

        //尝试在主线程获取结果
        //如果 FutureTask 中的结果还没有生产呢,此时就会产生阻塞
//        一致等到最终的线程把这个结果算出来之后, get才会返回

        Integer result  = task.get();
        System.out.println(result);
    }
}
