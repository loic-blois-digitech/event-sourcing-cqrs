package com.example.eventsourcingcqrs.command.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
@Data
@AllArgsConstructor
public class AccountAggregate {
    private String accountId;
    private BigDecimal balance;
    private String status;
}
