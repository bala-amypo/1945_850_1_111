package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match-attempts")
public class MatchAttemptRecordController {

    // 1) FIELD INJECTION VIA CONSTRUCTOR
    private final MatchAttemptRecordService service;

    // 2) CONSTRUCTOR – Spring injects the ServiceImpl here
    public MatchAttemptRecordController(MatchAttemptRecordService service) {
        this.service = service;
    }

    // POST /api/match-attempts?initiatorId=1&candidateId=3
    @PostMapping
    public ResponseEntity<MatchAttemptRecord> createAttempt(
            @RequestParam Long initiatorId,
            @RequestParam Long candidateId) {

        MatchAttemptRecord created = service.createAttempt(initiatorId, candidateId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PATCH /api/match-attempts/{id}/approve
    @PatchMapping("/{id}/approve")
    public ResponseEntity<MatchAttemptRecord> approve(@PathVariable Long id) {
        MatchAttemptRecord updated = service.approveAttempt(id);
        return ResponseEntity.ok(updated);
    }

    // PATCH /api/match-attempts/{id}/reject
    @PatchMapping("/{id}/reject")
    public ResponseEntity<MatchAttemptRecord> reject(@PathVariable Long id) {
        MatchAttemptRecord updated = service.rejectAttempt(id);
        return ResponseEntity.ok(updated);
    }
}
