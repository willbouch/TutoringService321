package ca.mcgill.ecse321.tutoringservice321;

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
import ca.mcgill.ecse321.tutoringservice321.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutoringService321Repository;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import ca.mcgill.ecse321.tutoringservice321.model.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TutoringService321ApplicationTests {
   
	@Test
    public void contextLoads() {

    }
	
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

	@After
	public void clearDatabase() {
		tutorRepository.deleteAll();
		availabilityRepository.deleteAll();
		sessionRepository.deleteAll();
		courseRepository.deleteAll();
		subjectRepository.deleteAll();
	}

	/*
	 * ***** Tests for Tutor--Kyjauna
	 */

	@Test
	public void testCreateTutor() {

		String username = "aName";
		String email = "test.tester@mcgill.ca";
		String password = "Ilovedogs";
		String phoneNumber = "5145555555";
		int hourlyRate = 25;

		try {
			service.createTutor(username, email, password, phoneNumber, hourlyRate);
			List<Tutor> allTutors = service.getAllTutors();

			assertEquals(1, allTutors.size());
			assertEquals(username, allTutors.get(0).getUserID());
			assertEquals(password, allTutors.get(0).getPassword());
			assertEquals(hourlyRate, allTutors.get(0).getHourlyRate(), 0.05);
			assertEquals(email, allTutors.get(0).getEmail());

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}

	}

	@Test
	public void testUpdateTutor() {

		String username = "aName";
		String email = "test.tester@mcgill.ca";
		String password = "Ilovedogs";
		String phoneNumber = "5145555555";
		int hourlyRate = 25;

		try {
			service.createTutor(username, email, password, phoneNumber, hourlyRate);
			List<Tutor> allTutors = service.getAllTutors();
			assertEquals(1, allTutors.size());

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}


		String newUsername = "anUpdatedName";
		String newEmail = "test.newtester@mcgill.ca";
		String newPassword = "Ichangedmymind,ilovecats";
		String newPhoneNumber = "5147777777";
		int newHourlyRate = 30;

		try {
			service.createTutor(newUsername, newEmail, newPassword, newPhoneNumber, newHourlyRate);
			List<Tutor> allTutors = service.getAllTutors();
			assertEquals(username, allTutors.get(0).getUserID());
			assertEquals(password, allTutors.get(0).getPassword());
			assertEquals(newHourlyRate, allTutors.get(0).getHourlyRate(), 30);

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}
	}

	@Test
	public void testCreateTutorNullUsername() {

		String username = null;
		String email = "test.tester@mcgill.ca";
		String password = "Ilovedogs";
		String phoneNumber = "5145555555";
		int hourlyRate = 25;


		try {
			service.createTutor(username, email, password, phoneNumber, hourlyRate);
			List<Tutor> allTutors = service.getAllTutors();
			assertEquals(0, allTutors.size());
			fail();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred

		}

	}

	@Test
	public void testCreateTutorNullPassword() {

		String username = "aName";
		String email = "test.tester@mcgill.ca";
		String password = null;
		String phoneNumber = "5145555555";
		int hourlyRate = 25;

		try {
			service.createTutor(username, email, password, phoneNumber, hourlyRate);
			List<Tutor> allTutors = service.getAllTutors();
			assertEquals(0, allTutors.size());
			fail();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}

	}

	@Test
	public void testCreateTutorWrongEmail() {

		/*
		 * String username = "email"; String password = "test"; double hr = 12; int exp
		 * = 3;
		 */

		String username = null;
		String password = "Ilovedogs";
		String phoneNumber = "5145555555";
		int hourlyRate = 25;



		try {
			service.createTutor(username, "wrong email", password, phoneNumber, hourlyRate);
			fail();
			List<Tutor> allTutors = service.getAllTutors();
			assertEquals(0, allTutors.size());
		} catch (IllegalArgumentException e) {
			// Check that no error occurred

		}
	}


	/*****
	 * Tests for session --Sharon
	 */

	@Test
	public void testWriteSession() {
		assertEquals(0, service.getAllSessions().size());

		long millis=System.currentTimeMillis();  		
		Date date = Date.valueOf("2019-11-13");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");

		String tutorEmail = "k@gmail.com";

		try {
			service.createTutor(tutorEmail, "Katie", "123", "432", 34);
			service.createSession(tutorEmail, date, endTime, startTime);
			fail();

			List<Session> allSessions = service.getAllSessions();

			assertEquals(1, allSessions.size());
			assertEquals(date, allSessions.get(0).getDate());
			assertEquals(startTime, allSessions.get(0).getStarTime());
			assertEquals(endTime, allSessions.get(0).getEndTime());

		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testWriteSessionNullDate() {
		assertEquals(0, service.getAllSessions().size());

		Date date = (Date) null;
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");

		String tutorEmail = "k@gmail.com";

		try {
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllSessions().size());
		}
	}

	@Test
	public void testWriteSessionNullStartTime() {
		assertEquals(0, service.getAllSessions().size());

		Date date = Date.valueOf("2020-01-10");
		Time startTime = (Time) null;
		Time endTime = Time.valueOf("16:00:00");

		String tutorEmail = "k@gmail.com";

		try {
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllSessions().size());
		}
	}

	@Test
	public void testWriteSessionNullEndTime() {
		assertEquals(0, service.getAllSessions().size());

		Date date = Date.valueOf("2020-01-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = (Time) null;

		String tutorEmail = "k@gmail.com";

		try {
			service.createTutor("k@gmail.com", "Katie", "123", "432", 34);
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllSessions().size());
		}
	}


	/******
	 * Tests for Availability--Sharon
	 */
	@Test
	public void testWriteAvailability() {
		String tutorEmail = "h@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());

		Date date = Date.valueOf("2019-12-01");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");

		try {
			service.createTutor(tutorEmail, "Hadi", "123", "514356241", 20);
			service.addAvailability(tutorEmail, date, startTime, endTime);
			fail();

			List <Availability> allAvailabilities = service.getAllTutorAvailabilities(tutorEmail);

			assertEquals(1, allAvailabilities.size());
			assertEquals(date, allAvailabilities.get(0).getDate());
			assertEquals(startTime, allAvailabilities.get(0).getStartTime());
			assertEquals(endTime, allAvailabilities.get(0).getEndTime());
		} catch (IllegalArgumentException e) {
		}

	}


	@Test
	public void testViewAvailabilityNullDate() {
		String tutorEmail = "h@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());

		Date date = (Date) null;
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("16:00:00");

		try {
			service.createTutor(tutorEmail, "Hadi", "123", "514356241", 20);
			service.addAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		}
	}


	@Test
	public void testViewAvailabilityNullStartTime() {
		String tutorEmail = "h@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());

		Date date = Date.valueOf("2019-12-01");
		Time startTime = (Time) null;
		Time endTime = Time.valueOf("16:00:00");

		try {
			service.createTutor(tutorEmail, "Hadi", "123", "514356241", 20);
			service.addAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		}
	}


	@Test
	public void testViewAvailabilityNullEndTime() {
		String tutorEmail = "h@gmail.com";
		assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());

		Date date = Date.valueOf("2019-12-01");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = (Time) null;

		try {
			service.createTutor(tutorEmail, "Hadi", "123", "514356241", 20);
			service.addAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			assertEquals(0, service.getAllTutorAvailabilities(tutorEmail).size());
		}
	}

	/****
	 * Tests for subject--Kyjauna
	 */

	@Test
	public void testCreateSubject() {

		String subjectName = "aName";


		try {
			service.createSubject(subjectName);
			List<Subject> allSubjects = service.getAllSubjects();

			assertEquals(1, allSubjects.size());
			assertEquals(subjectName, allSubjects.get(0).getSubjectName());

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}
	}

	@Test
	public void testCreateSubjectNullName() {

		String subjectName = null;


		try {
			List<Subject> allSubjects = service.getAllSubjects();

			service.createSubject(subjectName);

			assertEquals(1, allSubjects.size());
			assertEquals(subjectName, allSubjects.get(0).getSubjectName());

			fail();

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}

	}
	/****
	 * Tests for course--Sharon
	 */

	@Test
	public void testCreateCourse() {

		String description = "new";
		String courseCode = "ECSE 321";
		String school = "McGill";

		try {
			service.createCourse(description, school, courseCode);
			List<Course> allCourses = service.getAllCourses();

			assertEquals(1, allCourses.size());
			assertEquals(school, allCourses.get(0).getSchool());
			assertEquals(description, allCourses.get(0).getDescription());
			assertEquals(courseCode, allCourses.get(0).getCourseCode());

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}
	}


	@Test
	public void testCreateCourseNullDescription() {

		String description = null;
		String courseCode = "ECSE 321";
		String school = "McGill";


		try {
			List<Course> allCourses = service.getAllCourses();
			service.createCourse(description, school, courseCode);
			fail();
			assertEquals(0, allCourses.size());

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}


	}

	@Test
	public void testCreateCourseNullCourseCode() {

		String description = "new";
		String courseCode = null;
		String school = "McGill";


		try {
			List<Course> allCourses = service.getAllCourses();
			service.createCourse(description, school, courseCode);
			fail();
			assertEquals(0, allCourses.size());

		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}

	}

	@Test
	public void testCreateCourseNullSchool() {

		String description = "new";
		String courseCode = "COMPSCI 301";
		String school = null;


		try {
			List<Course> allCourses = service.getAllCourses();
			service.createCourse(description, school, courseCode);
			fail();
			assertEquals(0, allCourses.size());

		} catch (IllegalArgumentException e){
		}

	}
}
