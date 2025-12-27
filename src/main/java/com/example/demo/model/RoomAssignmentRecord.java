package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roomassignmentrecord")
public class RoomAssignmentRecord {

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAId;
    private Long studentBId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public Long getId() {
        return id;
    }

    public Long getStudentAId() {
        return studentAId;
    }

    public void setStudentAId(Long studentAId) {
        this.studentAId = studentAId;
    }

    public Long getStudentBId() {
        return studentBId;
    }

    public void setStudentBId(Long studentBId) {
        this.studentBId = studentBId;
    }

    // ===== TEST-COMPATIBLE =====

    public String getStatus() {
        return status.name();
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
