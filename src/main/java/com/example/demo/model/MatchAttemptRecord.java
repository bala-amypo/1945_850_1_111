package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "match_attempt_record")
public class MatchAttemptRecord {

    public enum Status {
        PENDING_REVIEW,
        ACCEPTED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;
    private Long candidateStudentId;

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

    // 🔥 String-based (tests expect this)
    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    // 🔥 Enum-based (services)
    public void setStatus(Status status) {
        this.status = status;
    }
}
