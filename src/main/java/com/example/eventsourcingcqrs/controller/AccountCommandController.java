package com.example.eventsourcingcqrs.controller;

import com.example.eventsourcingcqrs.dto.AccountCreationDTO;
import com.example.eventsourcingcqrs.dto.MoneyAmountDTO;
import com.example.eventsourcingcqrs.entity.BankAccount;
import com.example.eventsourcingcqrs.service.AccountCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/account")
public class AccountCommandController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountCommandController.class);

    @Autowired
    private AccountCommandService accountCommandService;

    /**
     * Add a bank account.
     *
     * @param creationDTO
     * @return
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<BankAccount> createAccount(@RequestBody AccountCreationDTO creationDTO) {
        return this.accountCommandService.createAccount(creationDTO);
    }

    /**
     * Credit money to account.
     *
     * @param accountId
     * @param moneyCreditDTO
     * @return
     */
    @PutMapping(value = "/credit/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompletableFuture<BankAccount> creditMoneyToAccount(@PathVariable(value = "accountId") String accountId,
                                                               @RequestBody MoneyAmountDTO moneyCreditDTO) {
        return this.accountCommandService.creditMoneyToAccount(accountId, moneyCreditDTO);
    }

    /**
     * Debit money to account.
     *
     * @param accountId
     * @param moneyDebitDTO
     * @return
     */
    @PutMapping(value = "/debit/{accountId}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompletableFuture<BankAccount> debitMoneyToAccount(@PathVariable(value = "accountId") String accountId,
                                                              @RequestBody MoneyAmountDTO moneyDebitDTO) {
        return this.accountCommandService.debitMoneyToAccount(accountId, moneyDebitDTO);
    }
}
