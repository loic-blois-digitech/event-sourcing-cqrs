package com.example.eventsourcingcqrs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BankAccount implements Serializable {

    @Id
    private UUID id;
    private String owner;
    private BigDecimal balance;
}
