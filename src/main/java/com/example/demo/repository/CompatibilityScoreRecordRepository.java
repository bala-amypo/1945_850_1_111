public interface CompatibilityScoreRecordRepository extends JpaRepository<CompatibilityScoreRecord, Long> {
    Optional<CompatibilityScoreRecord> findByStudentAIdAndStudentBId(Long a, Long b);
    List<CompatibilityScoreRecord> findByStudentAIdOrStudentBId(Long a, Long b);
}
