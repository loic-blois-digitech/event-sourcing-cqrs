package com.example.eventsourcingcqrs.projection;

import com.example.eventsourcingcqrs.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BankAccountProjection {
    private final BankAccountRepository repository;
}
