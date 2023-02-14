package com.example.eventsourcingcqrs.event;

/**
 * @param <T>
 * @author l.blois
 */
public class BaseEvent<T> {

    private final T id;

    public BaseEvent(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
