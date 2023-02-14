package com.example.eventsourcingcqrs.command.api.commands;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
public class CreditMoneyCommand extends BaseCommand<String> {

    private final BigDecimal amount;

    public CreditMoneyCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
