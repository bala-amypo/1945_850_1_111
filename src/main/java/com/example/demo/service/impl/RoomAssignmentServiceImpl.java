package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private final RoomAssignmentRecordRepository roomRepository;
    private final StudentProfileRepository studentRepository;

    public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository roomRepository,
                                     StudentProfileRepository studentRepository) {
        this.roomRepository = roomRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {
        StudentProfile a = studentRepository.findById(assignment.getStudentAId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        StudentProfile b = studentRepository.findById(assignment.getStudentBId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (!a.isActive() || !b.isActive()) {
            throw new IllegalArgumentException("both students must be active");
        }

        assignment.setStatus("ACTIVE");
        return roomRepository.save(assignment);
    }

    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room assignment not found"));
    }

    @Override
    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
        return roomRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return roomRepository.findAll();
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord assignment = getAssignmentById(id);
        assignment.setStatus(status);
        return roomRepository.save(assignment);
    }
}
