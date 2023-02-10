package com.example.eventsourcingcqrs.command.controller;

import com.example.eventsourcingcqrs.command.dto.CreateAccountDTO;
import com.example.eventsourcingcqrs.command.dto.CreditDTO;
import com.example.eventsourcingcqrs.command.dto.DebitDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author l.blois
 */
@RestController
@RequestMapping(value = "/api/bank/account")
public class CommandBankAccountController {

    /**
     * Create a new bank account.
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/create")
    public ResponseEntity<String> createAccount(@RequestBody CreateAccountDTO request) {
        return null;
    }

    /**
     * Credit a bank account.
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/credited")
    public ResponseEntity<String> creditedAccount(@RequestBody CreditDTO request) {
        return null;
    }

    /**
     * Debit a bank account.
     *
     * @param request
     * @return
     */
    @PutMapping(value = "/debited")
    public ResponseEntity<String> debitedAccount(@RequestBody DebitDTO request) {
        return null;
    }
}
