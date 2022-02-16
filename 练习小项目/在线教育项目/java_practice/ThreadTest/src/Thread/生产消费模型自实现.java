package Thread;

public class 生产消费模型自实现 {
    static class BlockingQueue{
        private int[] items = new int[1000];
        private int head = 0;
        private int tail = 0;
        private int size =0;
        private Object locker = new Object();

        public void put(int item) throws InterruptedException {
            synchronized (locker) {
                //数组中的元素已满,阻塞队列
                if (size >= items.length) {
                    locker.wait();
                }

                //队列未满,写入元素
                items[tail] = item;
                size++;
                tail++;

                if (tail >= items.length) {
                    tail = 0;
                }

                //插入元素,队列中有元素了
                //唤醒tack中阻塞队列
                locker.notify();
            }
        }



            public int take() throws InterruptedException {
                int ret =0;
                synchronized (locker) {

                    //如果队列为空,阻塞线程
                    if (size == 0) {
                        locker.wait();
                    }

                    //不为空,正常读取
                    ret = items[head];
                    size--;
                    head++;

                    if (head >= items.length){
                        head =0;
                    }

                    //当队列中成功取出元素,队列就不满了,唤醒put中的阻塞的队列
                    locker.notify();

                }
                return ret;
            }
        }



    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue();

        //创建一个消费者线程
        Thread consumer = new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        int value = queue.take();
                        System.out.println("消费数据"+ value);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        consumer.start();

        //创建一个生产线数据
        Thread producer = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("生产元素" + i);
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
