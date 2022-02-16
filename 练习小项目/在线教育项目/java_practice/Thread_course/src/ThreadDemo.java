public class ThreadDemo {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("hello thread1");
    }
}
