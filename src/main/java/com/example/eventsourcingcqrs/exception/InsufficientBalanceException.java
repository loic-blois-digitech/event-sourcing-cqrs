package com.example.eventsourcingcqrs.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends Throwable{

    public InsufficientBalanceException(String id, BigDecimal amount) {
        super("Insufficient Balance: Cannot debit " + amount +
                " from account number [" + id + "]");
    }
}
