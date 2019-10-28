package ca.mcgill.ecse321.tutoringservice321.servicetests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice321.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Course;
import ca.mcgill.ecse321.tutoringservice321.model.Subject;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTests {
	@Mock 
	CourseRepository courseDao;

	@Mock
	TutorRepository tutorDao;
	
	@Mock
	SubjectRepository subjectDao;

	@InjectMocks
	private TutoringService321Service service;

	private static final String SCHOOL = "McGill";
	private static final String COURSE_CODE = "ECSE321";
	private static final String COURSE_DESCRIPTION = "A time consuming course";
	private static final String NONEXISTING_SCHOOL = "Concordia";
	private static final String TUTOR_EMAIL = "william@gmail.com";
	private static final String TUTOR_NAME = "TestTutor";
	private static final String TUTOR_PASSWORD = "TestPassword";
	private static final String TUTOR_PHONE = "6666666666";
	private static final int TUTOR_HOURLYRATE = 10;
	private static final Tutor TUTOR = new Tutor();

	@Before
	public void setMockOutput() {
		when(courseDao.findCourseBySchool(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(SCHOOL)) {
				Set<Course> courses = new HashSet<Course>();
				Course course = new Course();
				course.setCourseCode(COURSE_CODE);
				course.setDescription(COURSE_DESCRIPTION);
				course.setSchool(SCHOOL);
				courses.add(course);

				return courses;
			} else {
				return null;
			}
		});

		when(courseDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Set<Course> courses = new HashSet<Course>();
			Course course = new Course();
			course.setCourseCode(COURSE_CODE);
			course.setDescription(COURSE_DESCRIPTION);
			course.setSchool(SCHOOL);
			
			Course course2 = new Course();
			course2.setCourseCode(COURSE_CODE);
			course2.setDescription(COURSE_DESCRIPTION);
			course2.setSchool(SCHOOL);
			
			courses.add(course);
			courses.add(course2);

			return courses;
		});
		
		when(subjectDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			Set<Tutor> tutors1 = new HashSet<>();
			tutors1.add(TUTOR);
			
			Set<Tutor> tutors2 = new HashSet<>();
			tutors2.add(new Tutor());
			
			Set<Subject> subjects = new HashSet<Subject>();
			Subject subject1 = new Subject();
			subject1.setCourse(new HashSet<Course>());
			subject1.setTutor(tutors1);
			subject1.setSubjectName("Maths");
			
			Subject subject2 = new Subject();
			subject2.setCourse(new HashSet<Course>());
			subject2.setTutor(tutors2);
			subject2.setSubjectName("Java Programming");
			
			Course course1 = new Course();
			Course course2 = new Course();
			Course course3 = new Course();
			Course course4 = new Course();
			
			subject1.getCourse().add(course1);
			subject1.getCourse().add(course2);
			subject2.getCourse().add(course3);
			subject2.getCourse().add(course4);

			subjects.add(subject1);
			subjects.add(subject2);
			return subjects;
		});

		when(tutorDao.findTutorByEmail(anyString())).thenAnswer(
				(InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(TUTOR_EMAIL)) {
						TUTOR.setEmail(TUTOR_EMAIL);
						TUTOR.setName(TUTOR_NAME);
						TUTOR.setPassword(TUTOR_PASSWORD);
						TUTOR.setHourlyRate(TUTOR_HOURLYRATE);
						TUTOR.setPhoneNumber(TUTOR_PHONE);
						return TUTOR;
					} else {
						return null;
					}
				});
	}

	@Test
	public void testGetExistingCourse() {
		assertEquals(SCHOOL, service.getCourse(SCHOOL, COURSE_CODE).getSchool());
	}

	@Test
	public void testGetNonExistingCourse() {
		assertNull(service.getCourse(NONEXISTING_SCHOOL, COURSE_CODE));
	}

	@Test
	public void testGetCourseWrongCode() {
		assertNull(service.getCourse(SCHOOL, "1"));
	}

	@Test
	public void testRequestCourse() {
		String email = null;

		try {
			email = service.requestCourse(COURSE_CODE, TUTOR_EMAIL);
		} catch(IllegalArgumentException e) {
			fail();
		}

		assertEquals("Dear Manager,\n"
				+ "	I would like to offer a new course, "+COURSE_CODE+"\n"
				+ " but it is not currently part of the offered courses. \n"
				+ " Could you please make it available so I could teach it?\n"
				+ " Thank you,\n"
				+ " "+TUTOR_NAME, email);
	}

	@Test
	public void testRequestCourseWithNullCourseCode() {
		String error = null;

		try {
			service.requestCourse(null, TUTOR_EMAIL);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Course Code cannot be empty.", error);
	}

	@Test
	public void testRequestCourseWithEmptyCourseCode() {
		String error = null;

		try {
			service.requestCourse("", TUTOR_EMAIL);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Course Code cannot be empty.", error);
	}

	@Test
	public void testRequestCourseWithNullTutorEmail() {
		String error = null;

		try {
			service.requestCourse(COURSE_CODE, null);
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor Email cannot be empty.", error);
	}

	@Test
	public void testRequestCourseWithEmptyTutorEmail() {
		String error = null;

		try {
			service.requestCourse(COURSE_CODE, "");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor Email cannot be empty.", error);
	}

	@Test
	public void testRequestCourseTutorNotFound() {
		String error = null;

		try {
			service.requestCourse(COURSE_CODE, "aaa@gmail.com");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor could not be found.", error);
	}
	
	@Test
	public void testGetAllCourses() {
		assertEquals(2, service.getAllCourses().size());
	}
	
	@Test
	public void testGetAllTutorCourses() {
		assertEquals(2, service.getAllTutorCourses(TUTOR_EMAIL).size());
	}
	
	@Test
	public void testGetAllTutorCoursesTutorNotFound() {
		String error = null;
		
		try {
			service.getAllTutorCourses("hey");
		} catch(IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Tutor could not be found.", error);
	}
}
