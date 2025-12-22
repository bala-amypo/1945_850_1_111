package com.example.demo.service;

import com.example.demo.dto.RoomAssignmentDto;
import java.util.List;
import java.util.Optional;

public interface RoomAssignmentService {
    RoomAssignmentDto assignRoom(String roomNumber, Long studentAId, Long studentBId);
    Optional<RoomAssignmentDto> getAssignmentById(Long id);
    List<RoomAssignmentDto> getAssignmentsForStudent(Long studentId);
    void cancelAssignment(Long id);
}
