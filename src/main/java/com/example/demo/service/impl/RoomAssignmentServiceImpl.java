package com.example.demo.service.impl;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.RoomAssignmentService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private RoomAssignmentRecordRepository roomRepo;
    private StudentProfileRepository studentRepo;

    @Autowired
    public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository roomRepo,
                                   StudentProfileRepository studentRepo) {
        this.roomRepo = roomRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {
        StudentProfile studentA = studentRepo.findById(assignment.getStudentAId())
                .orElseThrow(() -> new ResourceNotFoundException("Student A not found"));
        StudentProfile studentB = studentRepo.findById(assignment.getStudentBId())
                .orElseThrow(() -> new ResourceNotFoundException("Student B not found"));

        if (!studentA.getActive() || !studentB.getActive()) {
            throw new IllegalArgumentException("Error: both students must be active");
        }

        assignment.setStatus(RoomAssignmentRecord.Status.ACTIVE.name());
        return roomRepo.save(assignment);
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord assignment = roomRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room assignment not found"));
        assignment.setStatus(status);
        return roomRepo.save(assignment);
    }

    @Override
    public Optional<RoomAssignmentRecord> getAssignmentById(Long id) {
        return roomRepo.findById(id);
    }

    @Override
    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
        return roomRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return roomRepo.findAll();
    }
}
