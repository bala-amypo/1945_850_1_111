package com.example.demo.repository;

import com.example.demo.model.CompatibilityScoreRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface CompatibilityScoreRecordRepository extends JpaRepository<CompatibilityScoreRecord, Long> {
    Optional<CompatibilityScoreRecord> findByStudentAIdAndStudentBId(Long studentAId, Long studentBId);
    List<CompatibilityScoreRecord> findByStudentAIdOrStudentBId(Long id1, Long id2);
    List<CompatibilityScoreRecord> findByStudentAIdOrStudentBIdOrderByScoreDesc(Long id1, Long id2);
    Optional<CompatibilityScoreRecord> findFirstByStudentAIdAndStudentBIdOrderByComputedAtDesc(Long studentAId, Long studentBId);
}