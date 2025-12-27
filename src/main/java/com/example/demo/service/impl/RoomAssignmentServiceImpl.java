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

    private final RoomAssignmentRecordRepository roomRepo;
    private final StudentProfileRepository studentRepo;

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

        if (!studentA.isActive() || !studentB.isActive()) {
            throw new IllegalArgumentException("both students must be active");
        }

        // TEST EXPECTS STRING STATUS
        assignment.setStatus(RoomAssignmentRecord.Status.ACTIVE.name());
        return roomRepo.save(assignment);
    }

    // ✅ ENUM VERSION (already expected by interface)
    @Override
    public RoomAssignmentRecord updateStatus(Long id, RoomAssignmentRecord.Status status) {
        RoomAssignmentRecord assignment = roomRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room assignment not found"));
        assignment.setStatus(status.name());
        return roomRepo.save(assignment);
    }

    // ✅ STRING VERSION (REQUIRED BY TESTS)
    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        return updateStatus(id, RoomAssignmentRecord.Status.valueOf(status));
    }

    @Override
    public List<RoomAssignmentRecord> getAllAssignments() {
        return roomRepo.findAll();
    }

    @Override
    public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
        return roomRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return roomRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room assignment not found"));
    }
}
