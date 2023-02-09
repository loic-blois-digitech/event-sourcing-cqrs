package com.example.eventsourcingcqrs.aggregate.event;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountCreatedEvent implements Serializable {

    private final UUID id;
    private final BigDecimal initialBalance;
    private final String owner;
}
