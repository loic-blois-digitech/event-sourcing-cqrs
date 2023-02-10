package com.example.eventsourcingcqrs.query.query;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author l.blois
 */
@Data
@AllArgsConstructor
public class FindAccountByIdQuery {
    private String accountId;
}
