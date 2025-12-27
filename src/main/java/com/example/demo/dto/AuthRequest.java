package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
@Tag(name = "Room Assignment", description = "Room assignment management")
public class RoomAssignmentController {
    
    private final RoomAssignmentService assignmentService;
    
    public RoomAssignmentController(RoomAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }
    
    @PostMapping
    public ResponseEntity<RoomAssignmentRecord> assign(@RequestBody RoomAssignmentRecord assignment) {
        return ResponseEntity.ok(assignmentService.assignRoom(assignment));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<RoomAssignmentRecord> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(assignmentService.updateStatus(id, status));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RoomAssignmentRecord>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByStudent(studentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RoomAssignmentRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<RoomAssignmentRecord>> getAll() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
}