package concurrent;



import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8,16,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        for(int i = 0; i<5; i++){
            int taskid = i;
            executor.execute(()->{
                System.out.println(String.valueOf(taskid));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executor.shutdown();
    }
}
