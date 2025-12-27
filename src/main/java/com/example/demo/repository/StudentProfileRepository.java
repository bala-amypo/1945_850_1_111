import org.springframework.data.jpa.repository.Query;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    Optional<StudentProfile> findByStudentId(String studentId);

    Optional<StudentProfile> findByEmail(String email);

    List<StudentProfile> findByIdIn(List<Long> ids);

    // ✅ REQUIRED BY TESTS (THIS IS THE KEY FIX)
    @Query("select distinct s.id as studentId from StudentProfile s")
    List<StudentIdView> findDistinctStudents();
}
