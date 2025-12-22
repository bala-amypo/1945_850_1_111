package com.example.demo.repository;

import com.example.demo.model.RoomAssignmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomAssignmentRecordRepository extends JpaRepository<RoomAssignmentRecord, Long> {
}
