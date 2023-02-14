package com.example.eventsourcingcqrs.command.api.commands;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author l.blois
 **/
public class BaseCommand<T> {

    @TargetAggregateIdentifier
    private final T id;

    public BaseCommand(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}

