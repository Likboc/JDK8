package design.creational.singleton;

/**
 * 饿汉式单例
 */
public class EagerSingleton {
    private static EagerSingleton singleton = new EagerSingleton();

    static EagerSingleton get() {
        return singleton;
    }

}
