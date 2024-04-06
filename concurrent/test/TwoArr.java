package concurrent.test;

/**
 * 交替打印两个数组
 */
public class TwoArr {

    int[] arr1 = new int[]{1, 3, 5, 7, 9};
    int[] arr2 = new int[]{2, 4, 6, 8, 10};

    boolean flag;

    public synchronized void print1(){
        for(int i= 0; i < arr1.length; i++){
            while (flag){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            flag = !flag;
            System.out.println(arr1[i]);
            notifyAll();
        }
    }

    public synchronized void print2(){
        for(int i= 0; i < arr2.length; i++){
            while (!flag){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            flag = !flag;
            System.out.println(arr2[i]);
            notifyAll();
        }
    }

    public static void main(String[] args) {

        TwoArr twoArr = new TwoArr();

        new Thread(new Runnable() {
            @Override
            public void run() {
                twoArr.print1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                twoArr.print2();
            }
        }).start();
    }

}
