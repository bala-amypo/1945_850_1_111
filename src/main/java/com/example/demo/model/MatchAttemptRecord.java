package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "match_attempt_record")
@Data
public class MatchAttemptRecord {
    public enum Status {
        PENDINGREVIEW, MATCHED, REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long initiatorStudentId;

    @Column(nullable = false)
    private Long candidateStudentId;

    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDINGREVIEW;
}
