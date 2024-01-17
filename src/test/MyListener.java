package test;

import java.util.EventListener;

public class MyListener implements EventListener {
    void doorEvent(DoorEvent doorEvent) {
        System.out.println("door open");
    }
}
