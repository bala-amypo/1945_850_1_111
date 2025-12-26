// src/main/java/com/example/demo/model/MatchAttemptRecord.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matchattemptrecord")
public class MatchAttemptRecord {

    public enum Status {
        PENDING_REVIEW, MATCHED, REJECTED
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
    private Status status = Status.PENDING_REVIEW;

    // getters/setters omitted for brevity; include all needed by tests
    // ...
}
