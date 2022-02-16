package lock;

public class ThreadDemo4 {
    static  Object lock1 = new Object();
    static  Object lock2 = new Object();


    public static void main(String[] args) {
            Thread a = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized(lock1){
                        System.out.println(Thread.currentThread().getName() +"持有锁lock1,试图持有锁lock2");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized(lock2){
                            System.out.println(Thread.currentThread().getName()+"获取锁lock2");
                        }
                    }
                }
            });
            a.start();

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(lock2){
                    System.out.println(Thread.currentThread().getName() +"持有锁lock2,试图持有锁lock1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized(lock1){
                        System.out.println(Thread.currentThread().getName()+"获取锁lock1");
                    }
                }
            }
        });
        b.start();
    }

}


