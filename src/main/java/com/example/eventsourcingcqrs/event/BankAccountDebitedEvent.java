package com.example.eventsourcingcqrs.event;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
@Getter
public class BankAccountDebitedEvent extends BaseEvent<String>{
    private final BigDecimal amount;

    public BankAccountDebitedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
