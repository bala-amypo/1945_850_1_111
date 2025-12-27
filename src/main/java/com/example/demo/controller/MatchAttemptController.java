package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempt", description = "Match attempt management")
public class MatchAttemptController {
    
    private final MatchAttemptService attemptService;
    
    public MatchAttemptController(MatchAttemptService attemptService) {
        this.attemptService = attemptService;
    }
    
    @PostMapping
    public ResponseEntity<MatchAttemptRecord> log(@RequestBody MatchAttemptRecord attempt) {
        return ResponseEntity.ok(attemptService.logMatchAttempt(attempt));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<MatchAttemptRecord> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(attemptService.updateAttemptStatus(id, status));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MatchAttemptRecord>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(attemptService.getAttemptsByStudent(studentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MatchAttemptRecord> getById(@PathVariable Long id) {
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping
    public ResponseEntity<List<MatchAttemptRecord>> getAll() {
        return ResponseEntity.ok(attemptService.getAllMatchAttempts());
    }
}