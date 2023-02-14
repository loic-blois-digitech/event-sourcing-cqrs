package com.example.eventsourcingcqrs.query.api.controller;

import com.example.eventsourcingcqrs.command.api.data.entity.BankAccount;
import com.example.eventsourcingcqrs.query.api.model.QueryFindBankAccountById;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author l.blois
 */
@RestController
@RequestMapping(value = "/api/account")
public class QueryBankAccountController {

    @Autowired
    private QueryGateway queryGateway;

    /**
     * Rechercher un compte bancaire par son ID.
     *
     * @param accountId
     * @return
     */
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<BankAccount> findBankAccountById(@PathVariable(value = "accountId") String accountId) {
        BankAccount account = queryGateway
                .query(new QueryFindBankAccountById(accountId), BankAccount.class)
                .join();

        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
