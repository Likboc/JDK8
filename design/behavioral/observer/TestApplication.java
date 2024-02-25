package design.behavioral.observer;

public class TestApplication {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        Subscriber subscriber = new Subscriber();
        publisher.addSubs(subscriber);
        publisher.message();
    }
}
