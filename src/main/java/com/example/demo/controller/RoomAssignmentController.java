package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
public class RoomAssignmentController {

    private final RoomAssignmentService roomAssignmentService;

    public RoomAssignmentController(RoomAssignmentService roomAssignmentService) {
        this.roomAssignmentService = roomAssignmentService;
    }

    @PostMapping
    public RoomAssignmentRecord assign(@RequestBody RoomAssignmentRecord assignment) {
        return roomAssignmentService.assignRoom(assignment);
    }

    @PutMapping("/{id}/status")
    public RoomAssignmentRecord updateStatus(@PathVariable Long id,
                                             @RequestParam String status) {
        return roomAssignmentService.updateStatus(id, status);
    }

    @GetMapping("/{id}")
    public RoomAssignmentRecord getById(@PathVariable Long id) {
        return roomAssignmentService.getAssignmentById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<RoomAssignmentRecord> getByStudent(@PathVariable Long studentId) {
        return roomAssignmentService.getAssignmentsByStudent(studentId);
    }

    @GetMapping
    public List<RoomAssignmentRecord> listAll() {
        return roomAssignmentService.getAllAssignments();
    }
}
