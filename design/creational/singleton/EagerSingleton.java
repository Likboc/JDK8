package design.creational.singleton;

/**
 * 饿汉式单例
 */
public class EagerSingleton {
    private static final EagerSingleton singleton = new EagerSingleton();

    public static EagerSingleton getInstance() {
        return singleton;
    }

    private EagerSingleton() {

    }

}
