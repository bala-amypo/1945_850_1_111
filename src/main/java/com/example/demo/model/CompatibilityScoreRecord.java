package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility_score_records")
public class CompatibilityScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "student_a_id")
    private Long studentAId;
    
    @Column(name = "student_b_id")
    private Long studentBId;
    
    private Double score;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "compatibility_level")
    private CompatibilityLevel compatibilityLevel;
    
    @Column(name = "computed_at")
    private LocalDateTime computedAt = LocalDateTime.now();
    
    @Column(name = "details_json")
    private String detailsJson;
    
    public enum CompatibilityLevel { LOW, MEDIUM, HIGH, EXCELLENT }
    
    // Constructors
    public CompatibilityScoreRecord() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getStudentAId() { return studentAId; }
    public void setStudentAId(Long studentAId) { this.studentAId = studentAId; }
    
    public Long getStudentBId() { return studentBId; }
    public void setStudentBId(Long studentBId) { this.studentBId = studentBId; }
    
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    
    public CompatibilityLevel getCompatibilityLevel() { return compatibilityLevel; }
    public void setCompatibilityLevel(CompatibilityLevel compatibilityLevel) { this.compatibilityLevel = compatibilityLevel; }
    
    public LocalDateTime getComputedAt() { return computedAt; }
    public void setComputedAt(LocalDateTime computedAt) { this.computedAt = computedAt; }
    
    public String getDetailsJson() { return detailsJson; }
    public void setDetailsJson(String detailsJson) { this.detailsJson = detailsJson; }
}