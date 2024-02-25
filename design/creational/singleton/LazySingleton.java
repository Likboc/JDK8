package design.creational.singleton;

public class LazySingleton {
    private LazySingleton lazySingleton = null;

    public LazySingleton getInstance() {
        if(lazySingleton == null) {
            return new LazySingleton();
        }
        return lazySingleton;
    }
}
