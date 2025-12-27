package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roomassignmentrecord")
public class RoomAssignmentRecord {

    public enum Status {
        ACTIVE,
        COMPLETED,
        CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAId;
    private Long studentBId;
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    // ===== REQUIRED BY TESTS =====

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // 🔥 String-based (tests)
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
