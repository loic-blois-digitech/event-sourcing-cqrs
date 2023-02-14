package com.example.eventsourcingcqrs.exception;

public class AggregateNotFoundException extends RuntimeException {
    public AggregateNotFoundException() {
        super("Une erreur s'est produite: agrégat introuvable");
    }
}
