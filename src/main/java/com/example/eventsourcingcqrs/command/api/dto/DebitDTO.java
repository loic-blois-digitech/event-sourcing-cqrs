package com.example.eventsourcingcqrs.command.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author l.blois
 */
@Data
public class DebitDTO {

    private String accountId;
    private BigDecimal amount;
}
