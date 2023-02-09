package com.example.eventsourcingcqrs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping(value = "/welcome")
    public String displayWelcomeMessage() {
        return "Welcome to the app Event Sourcing!";
    }
}
