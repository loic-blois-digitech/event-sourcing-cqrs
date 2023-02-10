package com.example.eventsourcingcqrs.command.command;

/**
 * @author l.blois
 */
public class BaseCommand<T> {
    private final T id;

    public BaseCommand(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
