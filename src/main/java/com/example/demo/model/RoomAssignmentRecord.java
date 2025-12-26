package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roomassignmentrecord")
public class RoomAssignmentRecord {

    public enum Status {
        ACTIVE, COMPLETED, CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Long studentAId;

    @Column(nullable = false)
    private Long studentBId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    // getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Long getStudentAId() { return studentAId; }
    public void setStudentAId(Long studentAId) { this.studentAId = studentAId; }

    public Long getStudentBId() { return studentBId; }
    public void setStudentBId(Long studentBId) { this.studentBId = studentBId; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
