package com.example.eventsourcingcqrs.query.api.model;

import lombok.Data;

/**
 * @author l.blois
 */
@Data
public class QueryFindBankAccountById {
    private String accountId;

    public QueryFindBankAccountById(String accountId) {
        this.accountId = accountId;
    }
}
