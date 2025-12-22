package com.example.demo.service.impl;

import com.example.demo.dto.RoomAssignmentDto;
import com.example.demo.model.RoomAssignmentRecord;
import com.example.demo.repository.RoomAssignmentRecordRepository;
import com.example.demo.service.RoomAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomAssignmentServiceImpl implements RoomAssignmentService {
    
    @Autowired
    private RoomAssignmentRecordRepository roomAssignmentRepository;
    
    @Override
    public RoomAssignmentDto assignRoom(String roomNumber, Long studentAId, Long studentBId) {
        RoomAssignmentRecord assignment = new RoomAssignmentRecord();
        assignment.setRoomNumber(roomNumber);
        assignment.setStatus("ACTIVE");
        assignment.setAssignedAt(LocalDateTime.now());
        
        RoomAssignmentRecord saved = roomAssignmentRepository.save(assignment);
        return mapToDto(saved);
    }
    
    @Override
    public Optional<RoomAssignmentDto> getAssignmentById(Long id) {
        return roomAssignmentRepository.findById(id)
                .map(this::mapToDto);
    }
    
    @Override
    public List<RoomAssignmentDto> getAssignmentsForStudent(Long studentId) {
        return roomAssignmentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public void cancelAssignment(Long id) {
        roomAssignmentRepository.deleteById(id);
    }
    
    private RoomAssignmentDto mapToDto(RoomAssignmentRecord assignment) {
        return new RoomAssignmentDto(
                assignment.getId(),
                assignment.getRoomNumber(),
                assignment.getStatus(),
                assignment.getAssignedAt()
        );
    }
}
