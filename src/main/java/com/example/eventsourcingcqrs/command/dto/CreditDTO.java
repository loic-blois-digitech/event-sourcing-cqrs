package com.example.eventsourcingcqrs.command.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
@Data
public class CreditDTO {
    private String accountId;
    private BigDecimal amount;
}
