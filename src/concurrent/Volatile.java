package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Volatile {
    public static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while(!stop) {
                System.out.println("Running");
            }
        });
        ExecutorService executorService = Executors.newFixedThreadPool(9);
        for(int i = 0; i< 10; i++) {
            executorService.execute(new task());
        }

        t1.start();
        stop = true;
    }

    static class task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"is Running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
