package ca.mcgill.ecse321.tutoringservice321.persistencetests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ca.mcgill.ecse321.tutoringservice321.dao.AvailabilityRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutoringService321Repository;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import ca.mcgill.ecse321.tutoringservice321.model.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TutoringService321PersistenceTests {

	@Autowired
	TutoringService321Service service;

	@Autowired
	TutorRepository tutorRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	SessionRepository sessionRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	AvailabilityRepository availabilityRepository;
	@Autowired
	ReviewRepository reviewRepository;

	private Tutor tutor;
	private Session session;
	private Availability availability;
	private Review review;
	private Course course;
	private Subject subject;

	@Before
	@After
	public void clearDatabase() {
		availabilityRepository.deleteAll();
		sessionRepository.deleteAll();
		tutorRepository.deleteAll();
		courseRepository.deleteAll();
		subjectRepository.deleteAll();
		reviewRepository.deleteAll();
	}

	@Test
	public void testWriteTutor() {
		assertEquals(0, service.getAllTutors().size());

		String name = "aName";
		String email = "test.tester@mcgill.ca";
		String password = "Ilovedogs";
		String phoneNumber = "5145555555";
		int hourlyRate = 25;

		try {
			tutor = service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, service.getAllTutors().size());
		assertEquals(name, tutor.getName());
		assertEquals(password, tutor.getPassword());
		assertEquals(hourlyRate, tutor.getHourlyRate());
		assertEquals(email, tutor.getEmail());
		assertEquals(phoneNumber, tutor.getPhoneNumber());
	}

	@Test
	public void testUpdateTutor() {

		String username = "aName";
		String email = "test.tester@mcgill.ca";
		String password = "Ilovedogs";
		String phoneNumber = "5145555555";
		int hourlyRate = 25;

		try {
			tutor = service.createTutor(email, username, password, phoneNumber, hourlyRate);
			tutor = service.updateTutor(email, "Math", "4185730193", 18);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, service.getAllTutors().size());
		assertEquals("Math", tutor.getName());
		assertEquals(18, tutor.getHourlyRate());
		assertEquals("4185730193", tutor.getPhoneNumber());
	}

	@Test
	public void testViewTutor() {

		String username = "aName";
		String email = "test.tester@mcgill.ca";
		String password = "Ilovedogs";
		String phoneNumber = "5145555555";
		int hourlyRate = 25;

		try {
			service.createTutor(email, username, password, phoneNumber, hourlyRate);
			tutor = service.getTutor(email);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(username, tutor.getName());
		assertEquals(password, tutor.getPassword());
		assertEquals(hourlyRate, tutor.getHourlyRate());
		assertEquals(email, tutor.getEmail());
		assertEquals(phoneNumber, tutor.getPhoneNumber());
	}

	@Test
	public void testWriteSession() {

		Date date = Date.valueOf("2019-11-13");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");
		String tutorEmail = "k@gmail.com";

		try {
			tutor = service.createTutor(tutorEmail, "Katie", "123456789", "4185730193", 34);
			assertEquals(0, service.getAllSessions().size());
			session = service.createSession(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, service.getAllSessions().size());
		assertEquals(date, session.getDate());
		assertEquals(startTime, session.getStarTime());
		assertEquals(endTime, session.getEndTime());
	}

	@Test
	public void testViewSession() {

		Date date = Date.valueOf("2019-11-13");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");
		String tutorEmail = "k@gmail.com";

		try {
			tutor = service.createTutor(tutorEmail, "Katie", "123456789", "4185730193", 34);
			assertEquals(0, service.getAllSessions().size());
			service.createSession(tutorEmail, date, startTime, endTime);
			session = service.getSession(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(date, session.getDate());
		assertEquals(startTime, session.getStarTime());
		assertEquals(endTime, session.getEndTime());
	}

	@Test
	public void testWriteAvailability() {
		String tutorEmail = "k@gmail.com";

		Date date = Date.valueOf("2019-12-01");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");

		try {
			tutor = service.createTutor(tutorEmail, "Katie", "123456789", "4185730193", 20);
			assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
			availability = service.addAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, service.getAllTutorAvailabilities(tutorEmail).size());
		assertEquals(date, availability.getDate());
		assertEquals(startTime, availability.getStartTime());
		assertEquals(endTime, availability.getEndTime());
	}

	@Test
	public void testViewAvailability() {

		Date date = Date.valueOf("2019-11-13");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");
		String tutorEmail = "k@gmail.com";

		try {
			tutor = service.createTutor(tutorEmail, "Katie", "123456789", "4185730193", 34);
			service.addAvailability(tutorEmail, date, startTime, endTime);
			availability = service.getAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(date, availability.getDate());
		assertEquals(startTime, availability.getStartTime());
		assertEquals(endTime, availability.getEndTime());
	}

	@Test
	public void testWriteSubject() {
		assertEquals(0, service.getAllSubjects().size());

		String subjectName = "aName";

		try {
			subject = service.createSubject(subjectName);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, service.getAllSubjects().size());
		assertEquals(subjectName, subject.getSubjectName());
	}

	@Test
	public void testViewSubject() {

		String subjectName = "aName";

		try {
			service.createSubject(subjectName);
			subject = service.getSubject(subjectName);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(subjectName, subject.getSubjectName());
	}

	@Test
	public void testWriteCourse() {
		assertEquals(0, service.getAllCourses().size());

		String description = "new";
		String courseCode = "ECSE 321";
		String school = "McGill";

		try {
			course = service.createCourse(description, school, courseCode);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, service.getAllCourses().size());
		assertEquals(school, course.getSchool());
		assertEquals(description, course.getDescription());
		assertEquals(courseCode, course.getCourseCode());
	}

	@Test
	public void testViewCourse() {

		String description = "new";
		String courseCode = "ECSE 321";
		String school = "McGill";

		try {
			service.createCourse(description, school, courseCode);
			course = service.getCourse(school, courseCode);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(school, course.getSchool());
		assertEquals(description, course.getDescription());
		assertEquals(courseCode, course.getCourseCode());
	}

	@Test
	public void testWriteReview() {
		assertEquals(0, service.getAllReviews().size());

		String reviewText = "Good Session, thanks!";
		String tutorEmail = "william@gmail.com";
		Date date = Date.valueOf("2019-01-04");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("12:00:00");

		try {
			tutor = service.createTutor(tutorEmail, "William", "123456789", "4185730193", 20);
			session = service.createSession(tutorEmail, date, startTime, endTime);
			review = service.submitTutorReview(reviewText, tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, service.getAllReviews().size());
		assertEquals(tutorEmail, review.getAuthorEmail());
		assertEquals(reviewText, review.getTextualReview());
	}

	@Test
	public void testViewReview() {

		String reviewText = "Good Session, thanks!";
		String tutorEmail = "william@gmail.com";
		Date date = Date.valueOf("2019-01-04");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("12:00:00");

		try {
			tutor = service.createTutor(tutorEmail, "William", "123456789", "4185730193", 20);
			session = service.createSession(tutorEmail, date, startTime, endTime);
			service.submitTutorReview(reviewText, tutorEmail, date, startTime, endTime);
			review = service.getAllReviews().get(0);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(tutorEmail, review.getAuthorEmail());
		assertEquals(reviewText, review.getTextualReview());
	}
}
