package com.example.demo.controller;

import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.service.RoomAssignmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Room Assignments", description = "Room assignment management")
@RestController
@RequestMapping("/api/room-assignments")
public class RoomAssignmentController {

    private final RoomAssignmentService assignmentService;

    public RoomAssignmentController(RoomAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<RoomAssignmentRecord> assign(@RequestBody RoomAssignmentRecord assignment) {
        return ResponseEntity.ok(assignmentService.assignRoom(assignment));
    }

    @GetMapping
    public ResponseEntity<List<RoomAssignmentRecord>> getAll() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
}
