package design.creational.singleton;

public class LazySingleton {
    private LazySingleton lazySingleton = null;

    /**
     * 加双重锁
     * @return
     */
    public LazySingleton getInstance() {
        if(lazySingleton == null) {
            synchronized (LazySingleton.class) {
                if(lazySingleton == null) {
                    return new LazySingleton();
                }
            }
        }


        return lazySingleton;
    }

    private LazySingleton() {

    }
}
