public interface MatchAttemptRecordRepository extends JpaRepository<MatchAttemptRecord, Long> {
    List<MatchAttemptRecord> findByInitiatorStudentIdOrCandidateStudentId(Long a, Long b);
}
