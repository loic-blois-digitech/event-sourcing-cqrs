package com.example.eventsourcingcqrs.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyAmountDTO {
    private BigDecimal amount;
}
