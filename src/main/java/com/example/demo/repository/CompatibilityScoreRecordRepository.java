package com.example.demo.repository;

import com.example.demo.model.CompatibilityScoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompatibilityScoreRecordRepository
        extends JpaRepository<CompatibilityScoreRecord, Long> {

    Optional<CompatibilityScoreRecord> findByStudentAIdAndStudentBId(Long id1, Long id2);

    List<CompatibilityScoreRecord> findByStudentAIdOrStudentBIdOrderByScoreDesc(Long id1, Long id2);
}
