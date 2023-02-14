package com.example.eventsourcingcqrs.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String id) {
        super("Le numéro de compte bancaire " + id + " n'existe pas");
    }
}
