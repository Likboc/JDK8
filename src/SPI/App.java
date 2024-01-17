package SPI;

import java.util.ServiceLoader;

public class App {
    public static void main(String[] args) {
        ServiceLoader<Cat> serviceLoader = ServiceLoader.load(Cat.class);
        for(Cat i : serviceLoader) {
            i.miao();
        }
    }
}
