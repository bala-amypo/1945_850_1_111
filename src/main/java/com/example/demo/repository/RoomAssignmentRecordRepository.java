package com.example.demo.repository;

import com.example.demo.model.RoomAssignmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface RoomAssignmentRecordRepository extends JpaRepository<RoomAssignmentRecord, Long> {
    Optional<RoomAssignmentRecord> findByStudentAIdAndStudentBId(Long studentAId, Long studentBId);
    List<RoomAssignmentRecord> findByStudentAIdOrStudentBId(Long id1, Long id2);
    List<RoomAssignmentRecord> findByStudentAIdOrStudentBIdOrderByAssignedAtDesc(Long id1, Long id2);
}