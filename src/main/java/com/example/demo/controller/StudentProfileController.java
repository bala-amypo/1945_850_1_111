// package com.example.demo.controller;

// import com.example.demo.model.StudentProfile;
// import com.example.demo.service.StudentProfileService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/students")
// @Tag(name = "Student Profiles", description = "Student profile management")
// public class StudentProfileController {
    
//     private final StudentProfileService studentProfileService;
    
//     public StudentProfileController(StudentProfileService studentProfileService) {
//         this.studentProfileService = studentProfileService;
//     }
    
//     @PostMapping
//     @Operation(summary = "Create new student profile")
//     public ResponseEntity<StudentProfile> create(@RequestBody StudentProfile student) {
//         StudentProfile profile = studentProfileService.createStudent(student);
//         return ResponseEntity.status(HttpStatus.CREATED).body(profile);
//     }
    
//     @GetMapping("/{id}")
//     @Operation(summary = "Get student profile by ID")
//     public ResponseEntity<StudentProfile> getProfile(@PathVariable Long id) {
//         StudentProfile profile = studentProfileService.getStudentById(id);
//         return ResponseEntity.ok(profile);
//     }
    
//     @GetMapping
//     @Operation(summary = "Get all student profiles")
//     public ResponseEntity<List<StudentProfile>> getAllProfiles() {
//         List<StudentProfile> profiles = studentProfileService.getAllStudents();
//         return ResponseEntity.ok(profiles);
//     }
    
//     @PutMapping("/{id}/status")
//     @Operation(summary = "Update student status")
//     public ResponseEntity<StudentProfile> updateStatus(
//             @PathVariable Long id,
//             @RequestParam Boolean active) {
//         StudentProfile profile = studentProfileService.updateStudentStatus(id, active);
//         return ResponseEntity.ok(profile);
//     }
// }