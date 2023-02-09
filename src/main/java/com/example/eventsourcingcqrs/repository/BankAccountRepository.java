package com.example.eventsourcingcqrs.repository;

import com.example.eventsourcingcqrs.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
}
