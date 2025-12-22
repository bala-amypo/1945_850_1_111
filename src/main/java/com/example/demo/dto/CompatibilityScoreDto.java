package com.example.demo.dto;

import java.time.LocalDateTime;

public class CompatibilityScoreDto {

    private Long id;
    private Double score;
    private String compatibilityLevel;
    private String detailsJson;
    private LocalDateTime computedAt;

    public CompatibilityScoreDto() {
    }

    public CompatibilityScoreDto(Long id,
                                 Double score,
                                 String compatibilityLevel,
                                 String detailsJson,
                                 LocalDateTime computedAt) {
        this.id = id;
        this.score = score;
        this.compatibilityLevel = compatibilityLevel;
        this.detailsJson = detailsJson;
        this.computedAt = computedAt;
    }

    public Long getId() {
        return id;
    }

    public Double getScore() {
        return score;
    }

    public String getCompatibilityLevel() {
        return compatibilityLevel;
    }

    public String getDetailsJson() {
        return detailsJson;
    }

    public LocalDateTime getComputedAt() {
        return computedAt;
    }
}
