package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import org.springframework.http.ResponseEntity;

public class MatchAttemptController {

    private final MatchAttemptService service;

    public MatchAttemptController(MatchAttemptService service) {
        this.service = service;
    }

    public ResponseEntity<MatchAttemptRecord> log(MatchAttemptRecord a) {
        return ResponseEntity.ok(service.logMatchAttempt(a));
    }
}
