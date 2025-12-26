// RoomAssignmentService.java
package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;
import java.util.List;

public interface RoomAssignmentService {
    RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment);
    RoomAssignmentRecord updateStatus(Long id, RoomAssignmentRecord.Status status);
    List<RoomAssignmentRecord> getAllAssignments();
    List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId);
    RoomAssignmentRecord getAssignmentById(Long id);
}
