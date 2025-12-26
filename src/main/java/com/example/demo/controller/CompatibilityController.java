package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Compatibility", description = "Compatibility scoring")
@RestController
@RequestMapping("/api/compatibility")
public class CompatibilityController {

    private final CompatibilityScoreService compatService;

    public CompatibilityController(CompatibilityScoreService compatService) {
        this.compatService = compatService;
    }

    @PostMapping("/compute")
    public ResponseEntity<CompatibilityScoreRecord> computeScore(
            @RequestParam Long studentAId, 
            @RequestParam Long studentBId) {
        return ResponseEntity.ok(compatService.computeScore(studentAId, studentBId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CompatibilityScoreRecord>> getForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(compatService.getScoresForStudent(studentId));
    }
}
