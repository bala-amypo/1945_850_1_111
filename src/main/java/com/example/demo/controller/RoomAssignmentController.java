// package com.example.demo.controller;

// import com.example.demo.model.RoomAssignmentRecord;
// import com.example.demo.service.RoomAssignmentService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/room-assignments")
// @Tag(name = "Room Assignments", description = "Room assignment management")
// public class RoomAssignmentController {
    
//     private final RoomAssignmentService roomAssignmentService;
    
//     public RoomAssignmentController(RoomAssignmentService roomAssignmentService) {
//         this.roomAssignmentService = roomAssignmentService;
//     }
    
//     @PostMapping
//     @Operation(summary = "Assign room to compatible students")
//     public ResponseEntity<RoomAssignmentRecord> assign(@RequestBody RoomAssignmentRecord assignment) {
//         RoomAssignmentRecord created = roomAssignmentService.assignRoom(assignment);
//         return ResponseEntity.status(HttpStatus.CREATED).body(created);
//     }
    
//     @GetMapping("/{id}")
//     @Operation(summary = "Get room assignment by ID")
//     public ResponseEntity<RoomAssignmentRecord> getAssignment(@PathVariable Long id) {
//         RoomAssignmentRecord assignment = roomAssignmentService.getAssignmentById(id);
//         return ResponseEntity.ok(assignment);
//     }
    
//     @GetMapping("/student/{studentId}")
//     @Operation(summary = "Get all room assignments for a student")
//     public ResponseEntity<List<RoomAssignmentRecord>> getAssignmentsForStudent(
//             @PathVariable Long studentId) {
//         List<RoomAssignmentRecord> assignments = roomAssignmentService.getAssignmentsByStudent(studentId);
//         return ResponseEntity.ok(assignments);
//     }
    
//     @PutMapping("/{id}/status")
//     @Operation(summary = "Update assignment status")
//     public ResponseEntity<RoomAssignmentRecord> updateStatus(
//             @PathVariable Long id,
//             @RequestParam String status) {
//         RoomAssignmentRecord updated = roomAssignmentService.updateStatus(id, status);
//         return ResponseEntity.ok(updated);
//     }
// }