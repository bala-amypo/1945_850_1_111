package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempts", description = "Match attempt management")
public class MatchAttemptController {
    
    private final MatchAttemptService matchAttemptService;
    
    public MatchAttemptController(MatchAttemptService matchAttemptService) {
        this.matchAttemptService = matchAttemptService;
    }
    
    @PostMapping
    @Operation(summary = "Log match attempt")
    public ResponseEntity<MatchAttemptRecord> log(@RequestBody MatchAttemptRecord attempt) {
        MatchAttemptRecord logged = matchAttemptService.logMatchAttempt(attempt);
        return ResponseEntity.status(HttpStatus.CREATED).body(logged);
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update attempt status")
    public ResponseEntity<MatchAttemptRecord> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        MatchAttemptRecord updated = matchAttemptService.updateAttemptStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}