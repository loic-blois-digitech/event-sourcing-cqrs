package com.example.eventsourcingcqrs.command.api.service.command;

import com.example.eventsourcingcqrs.command.api.commands.CreateBankAccountCommand;
import com.example.eventsourcingcqrs.command.api.commands.CreditMoneyCommand;
import com.example.eventsourcingcqrs.command.api.commands.DebitMoneyCommand;
import com.example.eventsourcingcqrs.command.api.dto.CreateAccountDTO;
import com.example.eventsourcingcqrs.command.api.dto.CreditDTO;
import com.example.eventsourcingcqrs.command.api.dto.DebitDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author l.blois
 */
@Service
public class CommandBankAccountService {

    @Autowired
    private CommandGateway commandGateway;

    public CompletableFuture<String> createBankAccount(CreateAccountDTO dto) {
        return this.commandGateway.send(new CreateBankAccountCommand(
                UUID.randomUUID().toString(),
                dto.getStartingBalance())
        );
    }

    public CompletableFuture<String> depositMoneyInBankAccount(CreditDTO dto) {
        return this.commandGateway.send(new CreditMoneyCommand(
                dto.getAccountId(),
                dto.getAmount()
        ));
    }

    public CompletableFuture<String> withdrawalMoneyFromBankAccount(DebitDTO dto) {
        return commandGateway.send(new DebitMoneyCommand(
                dto.getAccountId(),
                dto.getAmount()
        ));
    }
}
