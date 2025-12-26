package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.RoomAssignmentService;

import java.util.*;

public class RoomAssignmentServiceImpl implements RoomAssignmentService {

    private final RoomAssignmentRecordRepository roomRepo;
    private final StudentProfileRepository studentRepo;

    public RoomAssignmentServiceImpl(
            RoomAssignmentRecordRepository roomRepo,
            StudentProfileRepository studentRepo) {
        this.roomRepo = roomRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public RoomAssignmentRecord assignRoom(RoomAssignmentRecord r) {

        StudentProfile a = studentRepo.findById(r.getStudentAId())
                .orElseThrow(() -> new RuntimeException("not found"));
        StudentProfile b = studentRepo.findById(r.getStudentBId())
                .orElseThrow(() -> new RuntimeException("not found"));

        if (!a.getActive() || !b.getActive()) {
            throw new IllegalArgumentException("both students must be active");
        }

        r.setStatus(RoomAssignmentRecord.Status.ACTIVE);
        return roomRepo.save(r);
    }

    @Override
    public RoomAssignmentRecord updateStatus(Long id, String status) {
        RoomAssignmentRecord r = roomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
        r.setStatus(RoomAssignmentRecord.Status.valueOf(status));
        return roomRepo.save(r);
    }

    @Override
    public RoomAssignmentRecord getAssignmentById(Long id) {
        return roomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
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
