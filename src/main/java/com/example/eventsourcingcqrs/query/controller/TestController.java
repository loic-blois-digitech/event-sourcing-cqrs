package com.example.eventsourcingcqrs.query.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping(value = "/welcome")
    public ResponseEntity<String> displayWelcomeMessage() {
        return new ResponseEntity<>("Welcome to the app Event Sourcing!", HttpStatus.OK);
    }
}
