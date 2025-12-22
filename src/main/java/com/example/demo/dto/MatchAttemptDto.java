package com.example.demo.dto;

import java.time.LocalDateTime;

public class MatchAttemptDto {

    private Long id;
    private String status;
    private LocalDateTime attemptedAt;

    public MatchAttemptDto() {
    }

    public MatchAttemptDto(Long id, String status, LocalDateTime attemptedAt) {
        this.id = id;
        this.status = status;
        this.attemptedAt = attemptedAt;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }
}
