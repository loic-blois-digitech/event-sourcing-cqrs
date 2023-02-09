package com.example.eventsourcingcqrs.projection;

import com.example.eventsourcingcqrs.aggregate.event.AccountCreatedEvent;
import com.example.eventsourcingcqrs.aggregate.event.MoneyCreditedEvent;
import com.example.eventsourcingcqrs.aggregate.event.MoneyDebitedEvent;
import com.example.eventsourcingcqrs.entity.BankAccount;
import com.example.eventsourcingcqrs.exception.AccountFoundException;
import com.example.eventsourcingcqrs.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BankAccountProjection {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountProjection.class);

    private final BankAccountRepository repository;

    /**
     * Add a new bank account.
     *
     * @param event
     */
    @EventHandler
    public void on(AccountCreatedEvent event) throws AccountFoundException {
        LOGGER.debug("Check if bank account {} exist in BDD", event.getId());
        Optional<BankAccount> optionalBankAccount = this.repository.findById(event.getId());

        if (!optionalBankAccount.isPresent()) {
            LOGGER.debug("Create a bank account {}", event.getId());
            BankAccount result = new BankAccount(
                    event.getId(),
                    event.getOwner(),
                    event.getInitialBalance()
            );

            LOGGER.debug("Save a bank account n°{} in BDD successful", event.getId());
            this.repository.save(result);
        } else {
            LOGGER.error("Add bank account {} in BDD unsuccessful", event.getId());
            throw new AccountFoundException(event.getId());
        }
    }

    /**
     * Add money in the bank account.
     *
     * @param event
     * @throws AccountNotFoundException
     */
    @EventHandler
    public void on(MoneyCreditedEvent event) throws com.example.eventsourcingcqrs.exception.AccountNotFoundException {
        LOGGER.debug("Check if bank account {} exist in BDD", event.getId());
        Optional<BankAccount> optionalBankAccount = this.repository.findById(event.getId());

        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setBalance(bankAccount.getBalance().add(event.getCreditAmount()));
            this.repository.save(bankAccount);

        } else {
            LOGGER.error("Credited money in bank account {} unsuccessful", event.getId());
            throw new com.example.eventsourcingcqrs.exception.AccountNotFoundException(event.getId());
        }

    }

    /**
     * Substract money in bank account.
     *
     * @param event
     * @throws AccountNotFoundException
     */
    @EventHandler
    public void on(MoneyDebitedEvent event) throws com.example.eventsourcingcqrs.exception.AccountNotFoundException {
        LOGGER.debug("Check if bank account {} exist in BDD", event.getId());
        Optional<BankAccount> optionalBankAccount = this.repository.findById(event.getId());

        if (optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setBalance(bankAccount.getBalance().subtract(event.getDebitAmount()));
            this.repository.save(bankAccount);

        } else {
            LOGGER.error("Debited money in bank account {} unsuccessful", event.getId());
            throw new com.example.eventsourcingcqrs.exception.AccountNotFoundException(event.getId());
        }
    }
}
