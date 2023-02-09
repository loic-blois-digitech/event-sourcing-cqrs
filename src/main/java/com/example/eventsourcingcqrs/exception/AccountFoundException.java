package com.example.eventsourcingcqrs.exception;

import java.util.UUID;

public class AccountFoundException extends Throwable {
    public AccountFoundException(UUID id) {
        super("Account number [" + id + "] already exists");
    }
}
