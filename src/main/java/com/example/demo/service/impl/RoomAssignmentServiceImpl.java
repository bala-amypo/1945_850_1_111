// package com.example.demo.service.impl;

// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.model.RoomAssignmentRecord;
// import com.example.demo.model.StudentProfile;
// import com.example.demo.repository.RoomAssignmentRecordRepository;
// import com.example.demo.repository.StudentProfileRepository;
// import com.example.demo.service.RoomAssignmentService;
// import org.springframework.stereotype.Service;
// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class RoomAssignmentServiceImpl implements RoomAssignmentService {
    
//     private final RoomAssignmentRecordRepository roomAssignmentRecordRepository;
//     private final StudentProfileRepository studentProfileRepository;

//     public RoomAssignmentServiceImpl(RoomAssignmentRecordRepository roomAssignmentRecordRepository,
//             StudentProfileRepository studentProfileRepository) {
//         this.roomAssignmentRecordRepository = roomAssignmentRecordRepository;
//         this.studentProfileRepository = studentProfileRepository;
//     }
    
//     @Override
//     public RoomAssignmentRecord assignRoom(RoomAssignmentRecord assignment) {
//         StudentProfile studentA = studentProfileRepository.findById(assignment.getStudentAId())
//                 .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        
//         StudentProfile studentB = studentProfileRepository.findById(assignment.getStudentBId())
//                 .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        
//         if (!studentA.getActive() || !studentB.getActive()) {
//             throw new IllegalArgumentException("both students must be active");
//         }
        
//         assignment.setStatus(RoomAssignmentRecord.Status.ACTIVE);
//         assignment.setAssignedAt(LocalDateTime.now());
        
//         return roomAssignmentRecordRepository.save(assignment);
//     }
    
//     @Override
//     public RoomAssignmentRecord updateStatus(Long id, String status) {
//         RoomAssignmentRecord assignment = getAssignmentById(id);
//         assignment.setStatus(RoomAssignmentRecord.Status.valueOf(status));
//         return roomAssignmentRecordRepository.save(assignment);
//     }
    
//     @Override
//     public RoomAssignmentRecord getAssignmentById(Long id) {
//         return roomAssignmentRecordRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Room assignment not found"));
//     }
    
//     @Override
//     public List<RoomAssignmentRecord> getAssignmentsByStudent(Long studentId) {
//         return roomAssignmentRecordRepository.findByStudentAIdOrStudentBId(studentId, studentId);
//     }
    
//     @Override
//     public List<RoomAssignmentRecord> getAllAssignments() {
//         return roomAssignmentRecordRepository.findAll();
//     }
// }