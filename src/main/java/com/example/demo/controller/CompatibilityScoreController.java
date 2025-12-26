
// package com.example.demo.controller;

// import com.example.demo.dto.CompatibilityScoreDto;
// import com.example.demo.model.CompatibilityScoreRecord;
// import com.example.demo.service.CompatibilityScoreService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/compatibility")
// @Tag(name = "Compatibility", description = "Compatibility matching endpoints")
// public class CompatibilityScoreController {
    
//     private final CompatibilityScoreService compatibilityScoreService;
    
//     public CompatibilityScoreController(CompatibilityScoreService compatibilityScoreService) {
//         this.compatibilityScoreService = compatibilityScoreService;
//     }
    
//     @PostMapping("/compute")
//     @Operation(summary = "Compute compatibility score between two students")
//     public ResponseEntity<CompatibilityScoreDto> computeScore(
//             @RequestParam Long studentAId,
//             @RequestParam Long studentBId) {
//         CompatibilityScoreRecord score = compatibilityScoreService.computeScore(studentAId, studentBId);
//         return ResponseEntity.status(HttpStatus.CREATED).body(mapToDto(score));
//     }
    
//     @GetMapping("/{id}")
//     @Operation(summary = "Get compatibility score by ID")
//     public ResponseEntity<CompatibilityScoreDto> getScore(@PathVariable Long id) {
//         CompatibilityScoreRecord score = compatibilityScoreService.getScoreById(id);
//         return ResponseEntity.ok(mapToDto(score));
//     }
    
//     @GetMapping("/student/{studentId}")
//     @Operation(summary = "Get all compatibility scores for a student")
//     public ResponseEntity<List<CompatibilityScoreDto>> getScoresForStudent(
//             @PathVariable Long studentId) {
//         List<CompatibilityScoreDto> scores = compatibilityScoreService.getScoresForStudent(studentId)
//                 .stream()
//                 .map(this::mapToDto)
//                 .collect(Collectors.toList());
//         return ResponseEntity.ok(scores);
//     }
    
//     @GetMapping
//     @Operation(summary = "Get all compatibility scores")
//     public ResponseEntity<List<CompatibilityScoreDto>> getAllScores() {
//         List<CompatibilityScoreDto> scores = compatibilityScoreService.getAllScores()
//                 .stream()
//                 .map(this::mapToDto)
//                 .collect(Collectors.toList());
//         return ResponseEntity.ok(scores);
//     }
    
//     private CompatibilityScoreDto mapToDto(CompatibilityScoreRecord score) {
//         CompatibilityScoreDto dto = new CompatibilityScoreDto();
//         dto.setId(score.getId());
//         dto.setStudentAId(score.getStudentAId());
//         dto.setStudentBId(score.getStudentBId());
//         dto.setScore(score.getScore());
//         dto.setCompatibilityLevel(score.getCompatibilityLevel().toString());
//         dto.setDetailsJson(score.getDetailsJson());
//         dto.setComputedAt(score.getComputedAt());
//         return dto;
//     }
// }
