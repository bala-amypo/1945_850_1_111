package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matchattemptrecord")
public class MatchAttemptRecord {

    public enum Status {
        PENDING,
        MATCHED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;
    private Long candidateStudentId;
    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    // ===== Standard getters/setters =====

    public Long getId() {
        return id;
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

    // ===== 🔥 TEST-COMPATIBLE METHODS =====

    // Tests expect String
    public String getStatus() {
        return status.name();
    }

    // Tests pass String
    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    // Internal enum support (used by services)
    public void setStatus(Status status) {
        this.status = status;
    }
}
