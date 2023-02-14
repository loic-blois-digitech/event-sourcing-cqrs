package com.example.eventsourcingcqrs.command.api.aggregate;

import com.example.eventsourcingcqrs.command.api.commands.CreateBankAccountCommand;
import com.example.eventsourcingcqrs.command.api.commands.CreditMoneyCommand;
import com.example.eventsourcingcqrs.command.api.commands.DebitMoneyCommand;
import com.example.eventsourcingcqrs.event.BankAccountActivatedEvent;
import com.example.eventsourcingcqrs.event.BankAccountCreatedEvent;
import com.example.eventsourcingcqrs.event.BankAccountCreditedEvent;
import com.example.eventsourcingcqrs.event.BankAccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @author l.blois
 **/
@Aggregate
public class BankAccountAggregate {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountAggregate.class);

    @AggregateIdentifier
    private String accountId;
    private BigDecimal balance;
    private String status;

    public BankAccountAggregate() {
    }

    @CommandHandler
    public BankAccountAggregate(CreateBankAccountCommand createBankAccountCommand) {
        LOGGER.debug("Une commande pour la création d'un nouveau compte bancaire [{}] a été effectué.", createBankAccountCommand.getId());
        AggregateLifecycle.apply(new BankAccountCreatedEvent(
                createBankAccountCommand.getId(),
                createBankAccountCommand.getBalance()));
    }

    @EventSourcingHandler
    public void on(BankAccountCreatedEvent accountCreatedEvent) {
        LOGGER.debug("La création d'un compte bancaire n°{} est en cours...", accountCreatedEvent.getId());
        this.accountId = accountCreatedEvent.getId();
        this.balance = accountCreatedEvent.getBalance();
        this.status = "CREATED";

        AggregateLifecycle.apply(new BankAccountActivatedEvent(this.accountId, "ACTIVATED"));
    }

    @EventSourcingHandler
    public void on(BankAccountActivatedEvent accountActivatedEvent) {
        LOGGER.debug("Une demande d'activation du compte n°{} est en cours...", accountActivatedEvent.getId());
        this.status = accountActivatedEvent.getStatus();
    }

    @CommandHandler
    public void on(CreditMoneyCommand creditMoneyCommand) {
        LOGGER.debug("Une commande pour un dépôt d'argent d'un montant de " + creditMoneyCommand.getAmount() + " sur le compte bancaire n°{} a été effectué.", creditMoneyCommand.getId());
        AggregateLifecycle.apply(new BankAccountCreditedEvent(
                creditMoneyCommand.getId(),
                creditMoneyCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(BankAccountCreditedEvent accountCreditedEvent) {
        LOGGER.debug("Une demande de dépôt d'argent sur le compte bancaire n°{} est en cours...", accountCreditedEvent.getId());
        this.balance = this.balance.add(accountCreditedEvent.getAmount());
    }

    @CommandHandler
    public void on(DebitMoneyCommand debitMoneyCommand) {
        LOGGER.debug("Une commande pour un retrait d'argent d'un montant de " + debitMoneyCommand.getAmount() + " sur le compte bancaire n°{} a été effectué.", debitMoneyCommand.getId());
        AggregateLifecycle.apply(new BankAccountDebitedEvent(
                debitMoneyCommand.getId(),
                debitMoneyCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(BankAccountDebitedEvent accountDebitedEvent) {
        LOGGER.debug("Une demande de retrait sur le compte bancaire n°{} est en cours...", accountDebitedEvent.getId());
        this.balance = this.balance.subtract(accountDebitedEvent.getAmount());
    }
}
