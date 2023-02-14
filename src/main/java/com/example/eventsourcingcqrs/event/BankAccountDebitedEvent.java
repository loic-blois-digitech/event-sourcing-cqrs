package com.example.eventsourcingcqrs.event;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
public class BankAccountDebitedEvent extends BaseEvent<String> {

    private final BigDecimal amount;

    public BankAccountDebitedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
