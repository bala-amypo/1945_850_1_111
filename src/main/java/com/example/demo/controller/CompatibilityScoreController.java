package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility Score", description = "Compatibility score management")
public class CompatibilityScoreController {
    
    private final CompatibilityScoreService compatService;
    
    public CompatibilityScoreController(CompatibilityScoreService compatService) {
        this.compatService = compatService;
    }
    
    @PostMapping("/compute/{studentAId}/{studentBId}")
    public ResponseEntity<CompatibilityScoreRecord> compute(@PathVariable Long studentAId, @PathVariable Long studentBId) {
        return ResponseEntity.ok(compatService.computeScore(studentAId, studentBId));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CompatibilityScoreRecord>> getForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(compatService.getScoresForStudent(studentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CompatibilityScoreRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(compatService.getScoreById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<CompatibilityScoreRecord>> getAll() {
        return ResponseEntity.ok(compatService.getAllScores());
    }
}