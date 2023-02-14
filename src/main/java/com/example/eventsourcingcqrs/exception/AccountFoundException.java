package com.example.eventsourcingcqrs.exception;

public class AccountFoundException extends RuntimeException {

    public AccountFoundException(String id) {
        super("Le numéro de compte bancaire " + id + " existe déjà");
    }
}
