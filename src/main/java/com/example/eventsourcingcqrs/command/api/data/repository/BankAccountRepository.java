package com.example.eventsourcingcqrs.command.api.data.repository;

import com.example.eventsourcingcqrs.command.api.data.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author l.blois
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
