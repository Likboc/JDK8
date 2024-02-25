package design.creational.singleton;

/**
 * 最优单例模式
 * 基于JVM进行加载，JVM自动保证线程安全
 */
public class IoDHSingleton {
    private static IoDHSingleton ioDHSingleton = null;

    public static IoDHSingleton getInstance() {
        if(ioDHSingleton.equals(null)) {
            ioDHSingleton = IoDH.ioDHSingleton;
            return ioDHSingleton;
        }
        return ioDHSingleton;
    }

    static class IoDH {
        public static IoDHSingleton ioDHSingleton = new IoDHSingleton();

    }
}
