package com.example.eventsourcingcqrs.command.api.commands;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
public class DebitMoneyCommand extends BaseCommand<String> {

    private final BigDecimal amount;

    public DebitMoneyCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
