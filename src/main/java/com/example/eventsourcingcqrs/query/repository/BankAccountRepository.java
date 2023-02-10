package com.example.eventsourcingcqrs.query.repository;

import com.example.eventsourcingcqrs.query.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author l.blois
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
