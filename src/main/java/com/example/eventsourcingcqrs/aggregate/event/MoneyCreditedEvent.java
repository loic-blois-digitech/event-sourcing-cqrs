package com.example.eventsourcingcqrs.aggregate.event;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Value
public class MoneyCreditedEvent implements Serializable {

    private final UUID id;
    private final BigDecimal creditAmount;
}
