package com.example.eventsourcingcqrs.event;

import lombok.Getter;

/**
 * @author l.blois
 */
@Getter
public class BankAccountActivatedEvent extends BaseEvent<String>{
    private final String status;

    public BankAccountActivatedEvent(String id, String status) {
        super(id);
        this.status = status;
    }
}
