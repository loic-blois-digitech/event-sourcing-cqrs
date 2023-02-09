package com.example.eventsourcingcqrs.exception;

import java.math.BigDecimal;
import java.util.UUID;

public class InsufficientBalanceException extends Throwable {
    public InsufficientBalanceException(UUID accountId, BigDecimal debitAmount) {
        super("Insufficient balance: Cannot debit " + debitAmount + " from account number [" + accountId.toString() + "]");
    }
}
