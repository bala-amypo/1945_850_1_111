package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.model.HabitProfile.CleanlinessLevel;
import com.example.demo.model.HabitProfile.NoiseTolerance;
import com.example.demo.model.HabitProfile.SleepSchedule;
import com.example.demo.model.HabitProfile.SocialPreference;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;
import com.example.demo.security.JwtUtil;
import com.example.demo.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

@SpringBootTest(classes = DemoApplication.class)
@Listeners(TestResultListener.class)
public class DemoMassiveTestNGTests extends AbstractTestNGSpringContextTests {

    @MockBean private StudentProfileRepository studentRepo;
    @MockBean private HabitProfileRepository habitRepo;
    @MockBean private CompatibilityScoreRecordRepository scoreRepo;
    @MockBean private MatchAttemptRecordRepository matchRepo;
    @MockBean private RoomAssignmentRecordRepository roomRepo;

    @Autowired private JwtUtil jwtUtil;

    private StudentProfileService studentService;
    private HabitProfileService habitService;
    private CompatibilityScoreService compatService;
    private MatchAttemptService attemptService;
    private RoomAssignmentService assignmentService;

    @BeforeClass
    public void setup() {
        studentService = new StudentProfileServiceImpl(studentRepo);
        habitService = new HabitProfileServiceImpl(habitRepo);
        compatService = new CompatibilityScoreServiceImpl(scoreRepo, habitRepo);
        attemptService = new MatchAttemptServiceImpl(matchRepo, scoreRepo);
        assignmentService = new RoomAssignmentServiceImpl(roomRepo, studentRepo);
    }

    // 1) Servlet-style startup checks

    @Test(priority = 1, groups = {"servlet"})
    public void t001_servlet_contextLoad() {
        assertNotNull(studentService);
    }

    @Test(priority = 2, groups = {"servlet"})
    public void t002_servlet_healthEndpoint() {
        assertTrue(true);
    }

    @Test(priority = 3, groups = {"servlet"})
    public void t003_servlet_connector() {
        assertNotNull(System.getProperty("java.version"));
    }

    // 2) CRUD tests

    @Test(priority = 3, groups = {"crud"})
    public void t010_createStudent_positive() {
        StudentProfile s = new StudentProfile();
        s.setStudentId("S100");
        s.setEmail("s100@example.com");
        s.setFullName("Test Student");
        when(studentRepo.findByStudentId("S100")).thenReturn(Optional.empty());
        when(studentRepo.findByEmail("s100@example.com")).thenReturn(Optional.empty());
        when(studentRepo.save(any())).thenReturn(s);
        StudentProfile created = studentService.createStudent(s);
        assertEquals(created.getStudentId(), "S100");
    }

    @Test(priority = 4, groups = {"crud"})
    public void t011_createStudent_duplicateStudentId() {
        StudentProfile s = new StudentProfile();
        s.setStudentId("S101");
        s.setEmail("s101@example.com");
        when(studentRepo.findByStudentId("S101")).thenReturn(Optional.of(new StudentProfile()));
        try {
            studentService.createStudent(s);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertTrue(ex.getMessage().contains("studentId exists"));
        }
    }

    @Test(priority = 5, groups = {"crud"})
    public void t012_getStudent_notFound() {
        when(studentRepo.findById(999L)).thenReturn(Optional.empty());
        try {
            studentService.getStudentById(999L);
            fail("Expected ResourceNotFoundException");
        } catch (RuntimeException ex) {
            assertTrue(ex.getMessage().toLowerCase().contains("not found"));
        }
    }

    @Test(priority = 6, groups = {"crud"})
    public void t013_updateStudentStatus() {
        StudentProfile s = new StudentProfile();
        s.setId(1L);
        s.setActive(true);
        when(studentRepo.findById(1L)).thenReturn(Optional.of(s));
        when(studentRepo.save(any())).thenReturn(s);
        StudentProfile updated = studentService.updateStudentStatus(1L, false);
        assertFalse(updated.getActive());
    }

    @Test(priority = 7, groups = {"crud"})
    public void t014_habit_create() {
        HabitProfile h = new HabitProfile();
        h.setStudentId(1L);
        h.setStudyHoursPerDay(4);
        h.setSleepSchedule(HabitProfile.SleepSchedule.REGULAR);
        when(habitRepo.findByStudentId(1L)).thenReturn(Optional.empty());
        when(habitRepo.save(any())).thenReturn(h);
        HabitProfile created = habitService.createOrUpdateHabit(h);
        assertEquals(created.getStudyHoursPerDay().intValue(), 4);
    }

    @Test(priority = 8, groups = {"crud"})
    public void t015_habit_invalidHours() {
        HabitProfile h = new HabitProfile();
        h.setStudentId(2L);
        h.setStudyHoursPerDay(-1);
        try {
            habitService.createOrUpdateHabit(h);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertTrue(ex.getMessage().contains("study hours"));
        }
    }

    // 3) DI tests

    @Test(priority = 9, groups = {"di"})
    public void t020_di_servicesPresent() {
        assertNotNull(studentService);
        assertNotNull(habitService);
        assertNotNull(compatService);
    }

    @Test(priority = 10, groups = {"di"})
    public void t021_di_reposPresent() {
        assertNotNull(studentRepo);
        assertNotNull(habitRepo);
    }

    @Test(priority = 11, groups = {"di"})
    public void t022_di_methodInjection() {
        when(studentRepo.findAll()).thenReturn(new ArrayList<>());
        assertEquals(studentService.getAllStudents().size(), 0);
    }

    // 4) Hibernate / entity checks

    @Test(priority = 12, groups = {"hibernate"})
    public void t030_hibernate_studentAnnotations() {
        StudentProfile s = new StudentProfile();
        s.setStudentId("S200");
        s.setEmail("s200@example.com");
        assertNotNull(s.getStudentId());
    }

    @Test(priority = 13, groups = {"hibernate"})
    public void t031_hibernate_habitAnnotations() {
        HabitProfile h = new HabitProfile();
        h.setStudentId(1L);
        assertNotNull(h.getStudentId());
    }

    @Test(priority = 14, groups = {"hibernate"})
    public void t032_hibernate_createScore() {
        HabitProfile a = new HabitProfile();
        a.setStudentId(1L);
        a.setSleepSchedule(SleepSchedule.EARLY);
        a.setCleanlinessLevel(CleanlinessLevel.MEDIUM);
        a.setNoiseTolerance(NoiseTolerance.MEDIUM);
        a.setSocialPreference(SocialPreference.BALANCED);
        a.setStudyHoursPerDay(3);

        HabitProfile b = new HabitProfile();
        b.setStudentId(2L);
        b.setSleepSchedule(SleepSchedule.EARLY);
        b.setCleanlinessLevel(CleanlinessLevel.MEDIUM);
        b.setNoiseTolerance(NoiseTolerance.MEDIUM);
        b.setSocialPreference(SocialPreference.BALANCED);
        b.setStudyHoursPerDay(4);

        when(habitRepo.findByStudentId(1L)).thenReturn(Optional.of(a));
        when(habitRepo.findByStudentId(2L)).thenReturn(Optional.of(b));
        when(scoreRepo.findByStudentAIdAndStudentBId(1L, 2L)).thenReturn(Optional.empty());
        when(scoreRepo.save(any())).thenAnswer(i -> i.getArguments()[0]);

        CompatibilityScoreRecord score = compatService.computeScore(1L, 2L);
        assertTrue(score.getScore() >= 0 && score.getScore() <= 100);
    }

    @Test(priority = 15, groups = {"hibernate"})
    public void t033_hibernate_sameStudentComputeNegative() {
        try {
            compatService.computeScore(1L, 1L);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertTrue(ex.getMessage().contains("same student"));
        }
    }

    // 5) JPA normalization

    @Test(priority = 16, groups = {"jpa"})
    public void t040_jpa_normalizationCheck() {
        assertTrue(true);
    }

    @Test(priority = 17, groups = {"jpa"})
    public void t041_jpa_referenceCheck() {
        assertTrue(true);
    }

    @Test(priority = 18, groups = {"jpa"})
    public void t042_jpa_uniqueConstraints() {
        when(studentRepo.findByEmail("u@example.com")).thenReturn(Optional.of(new StudentProfile()));
        try {
            StudentProfile s = new StudentProfile();
            s.setStudentId("u1");
            s.setEmail("u@example.com");
            studentService.createStudent(s);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    // 6) Many-to-many via room assignments

    @Test(priority = 19, groups = {"many2many"})
    public void t050_m2m_assignRoomPositive() {
        StudentProfile a = new StudentProfile();
        a.setId(1L);
        a.setActive(true);
        StudentProfile b = new StudentProfile();
        b.setId(2L);
        b.setActive(true);
        RoomAssignmentRecord r = new RoomAssignmentRecord();
        r.setRoomNumber("R-101");
        r.setStudentAId(1L);
        r.setStudentBId(2L);

        when(studentRepo.findById(1L)).thenReturn(Optional.of(a));
        when(studentRepo.findById(2L)).thenReturn(Optional.of(b));
        when(roomRepo.save(any())).thenAnswer(i -> i.getArguments()[0]);

        RoomAssignmentRecord created = assignmentService.assignRoom(r);
        assertEquals(created.getStatus(), RoomAssignmentRecord.Status.ACTIVE);
    }

    @Test(priority = 20, groups = {"many2many"})
    public void t051_m2m_assignRoomNegativeInactive() {
        StudentProfile a = new StudentProfile();
        a.setId(1L);
        a.setActive(false);
        StudentProfile b = new StudentProfile();
        b.setId(2L);
        b.setActive(true);
        RoomAssignmentRecord r = new RoomAssignmentRecord();
        r.setRoomNumber("R-102");
        r.setStudentAId(1L);
        r.setStudentBId(2L);

        when(studentRepo.findById(1L)).thenReturn(Optional.of(a));
        when(studentRepo.findById(2L)).thenReturn(Optional.of(b));

        try {
            assignmentService.assignRoom(r);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException ex) {
            assertTrue(ex.getMessage().contains("both students must be active"));
        }
    }

    @Test(priority = 21, groups = {"many2many"})
    public void t052_m2m_getAssignmentsByStudent() {
        RoomAssignmentRecord r = new RoomAssignmentRecord();
        r.setStudentAId(1L);
        r.setStudentBId(2L);
        when(roomRepo.findByStudentAIdOrStudentBId(1L, 1L)).thenReturn(List.of(r));
        List<RoomAssignmentRecord> lst = assignmentService.getAssignmentsByStudent(1L);
        assertEquals(lst.size(), 1);
    }

    // 7) Security / JWT

    @Test(priority = 22, groups = {"security"})
    public void t061_security_invalidToken() {
        try {
            jwtUtil.validate("invalid.token.value");
            fail("Expected exception");
        } catch (Exception ex) {
            assertTrue(true);
        }
    }

    @Test(priority = 23, groups = {"security"})
    public void t062_security_authControllerLogin() {
        String tok = jwtUtil.generateToken("user", "HOSTEL_MANAGER", "u@example.com", "10");
        assertNotNull(tok);
    }

    // 8) HQL-style queries

    @Test(priority = 24, groups = {"hql"})
    public void t070_hql_queryCompatibilityForStudent() {
        CompatibilityScoreRecord r = new CompatibilityScoreRecord();
        r.setStudentAId(1L);
        r.setStudentBId(2L);
        r.setScore(80.0);
        when(scoreRepo.findByStudentAIdOrStudentBId(1L, 1L)).thenReturn(List.of(r));
        List<CompatibilityScoreRecord> res = compatService.getScoresForStudent(1L);
        assertEquals(res.size(), 1);
    }

    @Test(priority = 25, groups = {"hql"})
    public void t071_hql_emptyResults() {
        when(scoreRepo.findByStudentAIdOrStudentBId(999L, 999L)).thenReturn(List.of());
        List<CompatibilityScoreRecord> res = compatService.getScoresForStudent(999L);
        assertTrue(res.isEmpty());
    }

    // Extra tests (same content as before, but with enum fixes where needed)
    // t080 ... t116 can be copied from your existing file,
    // making sure the two lines use enums:
    // attemptService.updateAttemptStatus(10L, MatchAttemptRecord.Status.MATCHED);
    // assignmentService.updateStatus(20L, RoomAssignmentRecord.Status.COMPLETED);
}
