package com.example.eventsourcingcqrs.exception;

public class AccountNotFoundException extends Throwable {

    public AccountNotFoundException(String id) {
        super("Bank account number " + id + " not exists");
    }
}
