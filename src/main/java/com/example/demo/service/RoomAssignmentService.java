package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;
import java.util.List;
import java.util.Optional;

public interface RoomAssignmentService {
    RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment);
    RoomAssignmentRecord updateStatus(Long id, String status);
   RoomAssignmentRecord getAssignmentById(Long id); 
    List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId);
    List<RoomAssignmentRecord> getAllAssignments();
}
