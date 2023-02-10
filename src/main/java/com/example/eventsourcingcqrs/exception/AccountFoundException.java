package com.example.eventsourcingcqrs.exception;

public class AccountFoundException extends Throwable {

    public AccountFoundException(String id) {
        super("Bank account number " + id + " already exists");
    }
}
