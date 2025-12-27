package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matchattemptrecord")
public class MatchAttemptRecord {

    public enum Status {
        
        PENDING_REVIEW,   // ✅ REQUIRED BY TESTS
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
    private Status status = Status.PENDING_REVIEW;

    // ===== REQUIRED BY TESTS =====

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

    // 🔥 STRING (tests)
    
    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    // 🔥 ENUM (services)
    public void setStatus(Status status) {
        this.status = status;
    }
    // 🔥 REQUIRED BY TESTS
public void setStatus(String status) {
    this.status = Status.valueOf(status);
}

// 🔥 REQUIRED BY TESTS
public Status getStatusEnum() {
    return status;
}

}
