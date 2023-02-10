package com.example.eventsourcingcqrs.event;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
@Getter
public class BankAccountCreatedEvent extends BaseEvent<String> {
    private final BigDecimal balance;

    public BankAccountCreatedEvent(String id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }
}
