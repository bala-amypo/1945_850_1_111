package com.example.demo.controller;

import com.example.demo.dto.RoomAssignmentDto;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-assignments")
public class RoomAssignmentController {

    @Autowired
    private RoomAssignmentService roomAssignmentService;

    @PostMapping
    public ResponseEntity<RoomAssignmentDto> assignRoom(
            @RequestParam String roomNumber,
            @RequestParam Long studentAId,
            @RequestParam Long studentBId) {

        RoomAssignmentDto dto =
                roomAssignmentService.assignRoom(roomNumber, studentAId, studentBId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomAssignmentDto> getAssignment(@PathVariable Long id) {
        return roomAssignmentService.getAssignmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RoomAssignmentDto>> getAssignmentsForStudent(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(roomAssignmentService.getAssignmentsForStudent(studentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelAssignment(@PathVariable Long id) {
        roomAssignmentService.cancelAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
