package concurrent.test;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 实战，用于多线程同时运行
 */
class TestThread extends Thread {
    private CountDownLatch count;

    TestThread(CountDownLatch latch) {
        this.count = latch;
    }
    @Override
    public void run() {
        System.out.println("count down");
        try{
            Thread.sleep(10000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        count.countDown();
    }
}
public class CountDown {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        TestThread testThread = new TestThread(countDownLatch);
        testThread.start();
        try {
            countDownLatch.await();
            System.out.println("hi");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
