package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility_score_record")
@Data
public class CompatibilityScoreRecord {
    public enum CompatibilityLevel {
        POOR, FAIR, GOOD, EXCELLENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentAId;

    @Column(nullable = false)
    private Long studentBId;

    @Column(nullable = false)
    private Double score;

    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;

    private String detailsJson;

    private LocalDateTime computedAt = LocalDateTime.now();
}
