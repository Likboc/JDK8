package design.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    List<Subscriber> list = new ArrayList<>();

    public void addSubs(Subscriber subscriber) {
        list.add(subscriber);
    }

    public void detach(Subscriber subscriber){
        list.remove(subscriber);
    }

    public  void message(){
        for(Subscriber s: list) {
            s.update();
        }
    }

}
