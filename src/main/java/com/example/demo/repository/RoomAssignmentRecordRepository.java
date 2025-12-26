public interface RoomAssignmentRecordRepository extends JpaRepository<RoomAssignmentRecord, Long> {
    List<RoomAssignmentRecord> findByStudentAIdOrStudentBId(Long a, Long b);
}
