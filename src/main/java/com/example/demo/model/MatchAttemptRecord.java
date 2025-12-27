package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempts")
public class MatchAttemptRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "initiator_student_id", nullable = false)
    private Long initiatorStudentId;

    @Column(name = "candidate_student_id", nullable = false)
    private Long candidateStudentId;

    @Column(name = "result_score_id")
    private Long resultScoreId;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Status {
        PENDINGREVIEW, MATCHED, REJECTED
    }

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

    public void setStatus(Status statusEnum) {
        this.status = statusEnum != null ? statusEnum.name() : null;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
