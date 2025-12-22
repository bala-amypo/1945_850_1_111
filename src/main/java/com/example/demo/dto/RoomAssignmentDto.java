package com.example.demo.dto;

import java.time.LocalDateTime;

public class RoomAssignmentDto {

    private Long id;
    private String roomNumber;
    private String status;
    private LocalDateTime assignedAt;

    public RoomAssignmentDto() {
    }

    public RoomAssignmentDto(Long id, String roomNumber,
                             String status,
                             LocalDateTime assignedAt) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.status = status;
        this.assignedAt = assignedAt;
    }

    public Long getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }
}
