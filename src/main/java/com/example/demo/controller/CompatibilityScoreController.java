package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
public class CompatibilityScoreController {

    private final CompatibilityScoreService scoreService;

    public CompatibilityScoreController(CompatibilityScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/compute/{studentAId}/{studentBId}")
    public CompatibilityScoreRecord compute(@PathVariable Long studentAId,
                                            @PathVariable Long studentBId) {
        return scoreService.computeScore(studentAId, studentBId);
    }

    @GetMapping("/student/{studentId}")
    public List<CompatibilityScoreRecord> getForStudent(@PathVariable Long studentId) {
        return scoreService.getScoresForStudent(studentId);
    }

    @GetMapping("/{id}")
    public CompatibilityScoreRecord getById(@PathVariable Long id) {
        return scoreService.getScoreById(id);
    }

    @GetMapping
    public List<CompatibilityScoreRecord> listAll() {
        return scoreService.getAllScores();
    }
}
