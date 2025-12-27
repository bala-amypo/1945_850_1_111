package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempt_records")
public class MatchAttemptRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "initiator_student_id")
    private Long initiatorStudentId;
    
    @Column(name = "candidate_student_id")
    private Long candidateStudentId;
    
    @Column(name = "result_score_id")
    private Long resultScoreId;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING_REVIEW;
    
    @Column(name = "attempted_at")
    private LocalDateTime attemptedAt = LocalDateTime.now();
    
    public enum Status { MATCHED, NOT_COMPATIBLE, PENDING_REVIEW }
    
    // Constructors
    public MatchAttemptRecord() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getInitiatorStudentId() { return initiatorStudentId; }
    public void setInitiatorStudentId(Long initiatorStudentId) { this.initiatorStudentId = initiatorStudentId; }
    
    public Long getCandidateStudentId() { return candidateStudentId; }
    public void setCandidateStudentId(Long candidateStudentId) { this.candidateStudentId = candidateStudentId; }
    
    public Long getResultScoreId() { return resultScoreId; }
    public void setResultScoreId(Long resultScoreId) { this.resultScoreId = resultScoreId; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public LocalDateTime getAttemptedAt() { return attemptedAt; }
    public void setAttemptedAt(LocalDateTime attemptedAt) { this.attemptedAt = attemptedAt; }
}