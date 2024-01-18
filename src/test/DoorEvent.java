package test;

import java.util.EventObject;

public class DoorEvent extends EventObject {
    public DoorEvent(Object source) {
        super(source);
    }
}
