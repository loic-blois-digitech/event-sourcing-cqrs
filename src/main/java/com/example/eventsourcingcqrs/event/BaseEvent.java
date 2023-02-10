package com.example.eventsourcingcqrs.event;

/**
 * @author l.blois
 * @param <T>
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
