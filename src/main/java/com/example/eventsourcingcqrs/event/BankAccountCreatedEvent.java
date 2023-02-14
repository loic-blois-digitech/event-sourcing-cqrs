package com.example.eventsourcingcqrs.event;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
public class BankAccountCreatedEvent extends BaseEvent<String> {

    private final BigDecimal balance;

    public BankAccountCreatedEvent(String id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
