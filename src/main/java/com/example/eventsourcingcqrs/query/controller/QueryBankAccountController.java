package com.example.eventsourcingcqrs.query.controller;

import com.example.eventsourcingcqrs.query.entity.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author l.blois
 */
@RestController
@RequestMapping(value = "/api/bank/account")
public class QueryBankAccountController {

    /**
     * Find account by ID.
     *
     * @param accountId
     * @return
     */
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<BankAccount> findAccountById(@PathVariable(value = "accountId") String accountId) {
        return null;
    }
}
