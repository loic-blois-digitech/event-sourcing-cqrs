package com.example.eventsourcingcqrs.command.api.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author l.blois
 */
@Entity
@Data
public class BankAccount implements Serializable {

    @Id
    private String accountId;
    private BigDecimal balance;
    private String status;
}
