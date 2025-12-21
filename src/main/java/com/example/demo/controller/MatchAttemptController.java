package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
public class MatchAttemptController {

    private final MatchAttemptService matchAttemptService;

    public MatchAttemptController(MatchAttemptService matchAttemptService) {
        this.matchAttemptService = matchAttemptService;
    }

    @PostMapping
    public MatchAttemptRecord log(@RequestBody MatchAttemptRecord attempt) {
        return matchAttemptService.logMatchAttempt(attempt);
    }

    @PutMapping("/{id}/status")
    public MatchAttemptRecord updateStatus(@PathVariable Long id,
                                           @RequestParam String status) {
        return matchAttemptService.updateAttemptStatus(id, status);
    }

    @GetMapping("/{id}")
    public MatchAttemptRecord getById(@PathVariable Long id) {
        return matchAttemptService.getAttemptById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<MatchAttemptRecord> getByStudent(@PathVariable Long studentId) {
        return matchAttemptService.getAttemptsByStudent(studentId);
    }

    @GetMapping
    public List<MatchAttemptRecord> listAll() {
        return matchAttemptService.getAllMatchAttempts();
    }
}
