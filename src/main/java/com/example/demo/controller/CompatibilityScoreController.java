package com.example.demo.controller;

import com.example.demo.dto.CompatibilityScoreDto;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
public class CompatibilityScoreController {

    @Autowired
    private CompatibilityScoreService compatibilityScoreService;

    @PostMapping("/compute")
    public ResponseEntity<CompatibilityScoreDto> computeScore(
            @RequestParam Long studentAId,
            @RequestParam Long studentBId) {

        CompatibilityScoreDto dto =
                compatibilityScoreService.computeCompatibilityScore(studentAId, studentBId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<CompatibilityScoreDto>> getAllScores() {
        return ResponseEntity.ok(compatibilityScoreService.getAllScores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompatibilityScoreDto> getScore(@PathVariable Long id) {
        return compatibilityScoreService.getScoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CompatibilityScoreDto>> getScoresForStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(compatibilityScoreService.getScoresForStudent(studentId));
    }
}
