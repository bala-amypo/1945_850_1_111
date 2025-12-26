package com.example.demo.service;

import com.example.demo.model.RoomAssignmentRecord;
import java.util.*;

public interface RoomAssignmentService {
    RoomAssignmentRecord assignRoom(RoomAssignmentRecord record);
    RoomAssignmentRecord updateStatus(Long id, String status);
    RoomAssignmentRecord getAssignmentById(Long id);
    List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId);
    List<RoomAssignmentRecord> getAllAssignments();
}
