package com.example.eventsourcingcqrs.exception;

public class AccountNotActivatedException extends RuntimeException {

    public AccountNotActivatedException(String id) {
        super("Le compte bancaire n° " + id + " n'est pas activé.");
    }
}
