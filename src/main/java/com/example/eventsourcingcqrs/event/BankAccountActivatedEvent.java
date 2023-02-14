package com.example.eventsourcingcqrs.event;

/**
 * @author l.blois
 */
public class BankAccountActivatedEvent extends BaseEvent<String>{

    private final String status;

    public BankAccountActivatedEvent(String id, String status) {
        super(id);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
