package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility_score")
@Data
public class CompatibilityScoreRecord {

    public enum CompatibilityLevel {
        POOR, FAIR, GOOD, EXCELLENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAId;
    private Long studentBId;

    private Double score;

    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;

    private String detailsJson;

    private LocalDateTime computedAt = LocalDateTime.now();
}
