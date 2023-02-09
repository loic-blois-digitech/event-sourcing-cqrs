package com.example.eventsourcingcqrs.aggregate;

import com.example.eventsourcingcqrs.aggregate.event.AccountCreatedEvent;
import com.example.eventsourcingcqrs.aggregate.target.CreateAccountCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
public class BankAccountAggregate implements Serializable {

    @AggregateIdentifier
    private UUID id;
    private BigDecimal balance;
    private String owner;

    @CommandHandler
    public BankAccountAggregate(CreateAccountCommand command) {
        AggregateLifecycle.apply(
          new AccountCreatedEvent(
                  command.getAccountId(),
                  command.getInitialBalance(),
                  command.getOwner()
          )
        );
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.owner = event.getOwner();
        this.balance = event.getInitialBalance();
    }
}
