package concurrent;


public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Object hi = new Object();
        Thread td = new Thread(()->{
            System.out.println("hi");
            synchronized (hi) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("hello,world");
            }
        });
        td.start();
        Thread td2 = new Thread(()->{
            System.out.println("hi");
            synchronized (hi) {
                System.out.println("hello,world");
            }
        });
        td2.start();
        Thread.sleep(1000);
        System.out.println(td2.getState());
    }
}
