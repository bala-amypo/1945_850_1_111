package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Match Attempts", description = "Match attempt management")
@RestController
@RequestMapping("/api/match-attempts")
public class MatchAttemptController {

    private final MatchAttemptService attemptService;

    public MatchAttemptController(MatchAttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping
    public ResponseEntity<MatchAttemptRecord> log(@RequestBody MatchAttemptRecord attempt) {
        return ResponseEntity.ok(attemptService.logMatchAttempt(attempt));
    }

    @GetMapping
    public ResponseEntity<List<MatchAttemptRecord>> getAll() {
        return ResponseEntity.ok(attemptService.getAllMatchAttempts());
    }
}
