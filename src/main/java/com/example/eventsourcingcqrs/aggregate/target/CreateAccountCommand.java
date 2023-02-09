package com.example.eventsourcingcqrs.aggregate.target;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountCommand implements Serializable {

    @TargetAggregateIdentifier
    private UUID accountId;
    private BigDecimal initialBalance;
    private String owner;
}
