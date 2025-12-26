package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "room_assignment_record")
@Data
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
}
