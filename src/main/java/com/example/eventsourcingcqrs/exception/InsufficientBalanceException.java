package com.example.eventsourcingcqrs.exception;

import java.math.BigDecimal;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String id, BigDecimal amount) {
        super("Solde insuffisant : impossible d'effectuer un retrait de " + amount + " sur le compte bancaire n° [" + id + "]");
    }
}
