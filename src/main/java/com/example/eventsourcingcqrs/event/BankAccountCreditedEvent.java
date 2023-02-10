package com.example.eventsourcingcqrs.event;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
@Getter
public class BankAccountCreditedEvent extends BaseEvent<String>{
    private final BigDecimal amount;

    public BankAccountCreditedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
