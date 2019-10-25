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
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Course;
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

		when(tutorDao.findTutorByEmail(anyString())).thenAnswer(
				(InvocationOnMock invocation) -> {
					if(invocation.getArgument(0).equals(TUTOR_EMAIL)) {
						Tutor tutor = new Tutor();
						tutor.setEmail(TUTOR_EMAIL);
						tutor.setName(TUTOR_NAME);
						tutor.setPassword(TUTOR_PASSWORD);
						tutor.setHourlyRate(TUTOR_HOURLYRATE);
						tutor.setPhoneNumber(TUTOR_PHONE);
						return tutor;
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
}
