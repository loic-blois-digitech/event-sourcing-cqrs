package com.example.eventsourcingcqrs.exception;

public class AccountAlreadyActivatedException extends RuntimeException {

    public AccountAlreadyActivatedException(String id) {
        super("Le compte bancaire n° " + id + " est déjà activé.");
    }
}
