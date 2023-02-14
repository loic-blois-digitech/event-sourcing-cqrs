package com.example.eventsourcingcqrs.command.api.commands;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
public class CreateBankAccountCommand extends BaseCommand<String> {

    private final BigDecimal balance;

    public CreateBankAccountCommand(String id, BigDecimal balance) {
        super(id);
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
