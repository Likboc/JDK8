package test;

public class Application {

    public static void main(String[] args) throws Exception {
        Runnable thd = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);

                    synchronized (this) {
                        notify();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread td1 = new Thread(thd);
        td1.start();
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    td1.join(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread td2 = new Thread(r2);
        td2.start();
        Thread.sleep(1000);
        System.out.println(td1.getState());
    }
}

