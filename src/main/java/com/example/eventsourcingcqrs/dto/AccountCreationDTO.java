package com.example.eventsourcingcqrs.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountCreationDTO {
    private BigDecimal initialBalance;
    private String owner;
}
