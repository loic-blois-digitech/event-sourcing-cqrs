package com.example.eventsourcingcqrs.query.api.service.projection;

import com.example.eventsourcingcqrs.command.api.data.entity.BankAccount;
import com.example.eventsourcingcqrs.event.BankAccountActivatedEvent;
import com.example.eventsourcingcqrs.event.BankAccountCreatedEvent;
import com.example.eventsourcingcqrs.event.BankAccountCreditedEvent;
import com.example.eventsourcingcqrs.event.BankAccountDebitedEvent;
import com.example.eventsourcingcqrs.exception.AccountAlreadyActivatedException;
import com.example.eventsourcingcqrs.exception.AccountFoundException;
import com.example.eventsourcingcqrs.exception.AccountNotActivatedException;
import com.example.eventsourcingcqrs.exception.AccountNotFoundException;
import com.example.eventsourcingcqrs.query.api.model.QueryFindBankAccountById;
import com.example.eventsourcingcqrs.command.api.data.repository.BankAccountRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author l.blois
 */
@Service
public class ProjectionBankAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectionBankAccountService.class);

    @Autowired
    private BankAccountRepository accountRepository;

    @EventHandler
    public void createBankAccount(BankAccountCreatedEvent event) throws AccountFoundException {
        String id = event.getId();
        LOGGER.debug("Création en base du compte n°{}.", event.getId());
        Optional<BankAccount> optionalBankAccount = this.accountRepository.findById(id);

        if (optionalBankAccount.isEmpty()) {
            BankAccount account = new BankAccount();
            account.setAccountId(event.getId());
            account.setBalance(event.getBalance());
            account.setStatus("CREATED");

            LOGGER.debug("Sauvegarde en base du compte n°{}.", event.getId());
            this.accountRepository.save(account);
        } else {
            LOGGER.error("Le compte bancaire n° {} existe déjà.", event.getId());
            throw new AccountFoundException(event.getId());
        }
    }

    @EventHandler
    public void activateBankAccount(BankAccountActivatedEvent event) throws AccountNotFoundException {

        String id = event.getId();
        LOGGER.debug("Activation du compte en cours...");
        Optional<BankAccount> account = this.accountRepository.findById(id);

        if (account.isPresent() && !account.get().getStatus().equals("ACTIVATED")) {
            account.get().setStatus("ACTIVATED");

            LOGGER.debug("Compte n°{} activé, sauvegarde en base du nouveau statut.", event.getId());
            this.accountRepository.save(account.get());

        } else if (account.isPresent() && account.get().getStatus().equals("ACTIVATED")) {
            LOGGER.error("Le compte bancaire n° {} est déjà activé.", event.getId());
            throw new AccountAlreadyActivatedException(event.getId());

        } else {
            LOGGER.error("Le compte bancaire n° {} n'existe pas.", event.getId());
            throw new AccountNotFoundException(event.getId());
        }
    }

    @EventHandler
    public void depositMoneyInBankAccount(BankAccountCreditedEvent event) throws AccountNotFoundException {

        LOGGER.debug("Ajout en base d'un dépôt d'argent d'un montant de {} sur le compte.", event.getAmount());
        Optional<BankAccount> account = this.accountRepository.findById(event.getId());

        if (account.isPresent() && account.get().getStatus().equals("ACTIVATED")) {
            account.get().setBalance(account.get().getBalance().add(event.getAmount()));

            LOGGER.debug("Sauvegarde en base du nouveau solde suite au dépôt effectué sur le compte n°{}.", event.getId());
            this.accountRepository.save(account.get());
        } else {
            if (account.isEmpty()) {
                LOGGER.error("Le compte bancaire n° {} n'existe pas.", event.getId());
                throw new AccountNotFoundException(event.getId());
            }

            if (account.get().getStatus().equals("CREATED")) {
                LOGGER.error("Le compte bancaire n° {} n'est pas activé.", event.getId());
                throw new AccountNotActivatedException(event.getId());
            }
        }
    }

    @EventHandler
    public void withdrawalMoneyFromBankAccount(BankAccountDebitedEvent event) throws AccountNotFoundException {

        LOGGER.debug("Retrait d'argent en base d'un montant de {} sur le compte.", event.getAmount());
        Optional<BankAccount> optionalBankAccount = this.accountRepository.findById(event.getId());

        if (optionalBankAccount.isPresent() && optionalBankAccount.get().getStatus().equals("ACTIVATED")) {
            BankAccount account = optionalBankAccount.get();
            account.setBalance(account.getBalance().subtract(event.getAmount()));

            LOGGER.debug("Sauvegarde en base du nouveau solde suite au retrait effectué sur le compte n°{}.", event.getId());
            this.accountRepository.save(account);
        } else {
            if (optionalBankAccount.isEmpty()) {
                LOGGER.error("Le compte bancaire n° {} n'existe pas.", event.getId());
                throw new AccountNotFoundException(event.getId());
            }

            if (optionalBankAccount.get().getStatus().equals("CREATED")) {
                LOGGER.error("Le compte bancaire n° {} n'est pas activé.", event.getId());
                throw new AccountNotActivatedException(event.getId());
            }
        }
    }

    @QueryHandler
    public BankAccount findBankAccountById(QueryFindBankAccountById query) throws AccountNotFoundException {

        LOGGER.debug("Rechercher un compte bancaire par son ID.");
        Optional<BankAccount> result = this.accountRepository.findById(query.getAccountId());

        if (result.isEmpty()) {
            LOGGER.error("Le compte bancaire n° {} n'existe pas.", query.getAccountId());
            throw new AccountNotFoundException(query.getAccountId());
        }

        LOGGER.debug("Compte bancaire trouver.");
        return result.get();
    }
}
