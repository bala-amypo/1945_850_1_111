package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempt_records")
public class MatchAttemptRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // initiator and candidate student IDs
    @Column(nullable = false)
    private Long initiatorStudentId;

    @Column(nullable = false)
    private Long candidateStudentId;

    // FK to CompatibilityScoreRecord
    @Column(nullable = false)
    private Long resultScoreId;

    // MATCHED / NOT_COMPATIBLE / PENDING_REVIEW
    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime attemptedAt = LocalDateTime.now();

    // === getters & setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInitiatorStudentId() {
        return initiatorStudentId;
    }

    public void setInitiatorStudentId(Long initiatorStudentId) {
        this.initiatorStudentId = initiatorStudentId;
    }

    public Long getCandidateStudentId() {
        return candidateStudentId;
    }

    public void setCandidateStudentId(Long candidateStudentId) {
        this.candidateStudentId = candidateStudentId;
    }

    public Long getResultScoreId() {
        return resultScoreId;
    }

    public void setResultScoreId(Long resultScoreId) {
        this.resultScoreId = resultScoreId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAttemptedAt() {
        return attemptedAt;
    }

    public void setAttemptedAt(LocalDateTime attemptedAt) {
        this.attemptedAt = attemptedAt;
    }
}
