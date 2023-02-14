package com.example.eventsourcingcqrs.command.api.controller;

import com.example.eventsourcingcqrs.command.api.dto.CreateAccountDTO;
import com.example.eventsourcingcqrs.command.api.dto.CreditDTO;
import com.example.eventsourcingcqrs.command.api.dto.DebitDTO;
import com.example.eventsourcingcqrs.exception.AggregateNotFoundException;
import com.example.eventsourcingcqrs.command.api.service.command.CommandBankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author l.blois
 */
@RestController
@RequestMapping(value = "/api/account")
public class CommandBankAccountController {

    @Autowired
    private CommandBankAccountService commandService;

    /**
     * Créez un nouveau compte bancaire.
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/create")
    public ResponseEntity<String> createBankAccount(@RequestBody CreateAccountDTO request) throws ExecutionException, InterruptedException {
        CompletableFuture<String> result = this.commandService.createBankAccount(request);
        return new ResponseEntity<>("Compte bancaire n° [" + result.get() + "] crée.", HttpStatus.CREATED);
    }

    /**
     * Déposer de l'argent sur un compte bancaire.
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/deposit")
    public ResponseEntity<String> depositMoneyInBankAccount(@RequestBody CreditDTO request) throws ExecutionException, InterruptedException {
        CompletableFuture<String> result = this.commandService.depositMoneyInBankAccount(request);

        if (!result.isCompletedExceptionally()) {
            return new ResponseEntity<>("Compte bancaire crédité.", HttpStatus.OK);
        } else {
            throw new AggregateNotFoundException();
        }
    }

    /**
     * Retrait d'argent sur un compte bancaire.
     *
     * @param request
     * @return
     */
    @PutMapping(value = "/debited")
    public ResponseEntity<String> withdrawalMoneyFromBankAccount(@RequestBody DebitDTO request) throws ExecutionException, InterruptedException {
        CompletableFuture result = this.commandService.withdrawalMoneyFromBankAccount(request);

        if (!result.isCompletedExceptionally()) {
            return new ResponseEntity<>("Compte bancaire débité.", HttpStatus.OK);
        } else {
            throw new AggregateNotFoundException();
        }
    }
}
