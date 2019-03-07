package org.juon.demowebmvc;

public class EventException extends RuntimeException {
    public EventException() {}

    public EventException(String message) {
        super(message);
    }
}
