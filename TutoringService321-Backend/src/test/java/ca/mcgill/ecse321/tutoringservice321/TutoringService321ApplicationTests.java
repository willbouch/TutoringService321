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
	@Autowired
	TutoringService321Service service;
	
	@Autowired
	TutorRepository tutorRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	SessionRepository sessionRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	AvailabilityRepository availabilityRepository;

	@After
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		availabilityRepository.deleteAll();
		// Then we can clear the other tables
		tutorRepository.deleteAll();
		sessionRepository.deleteAll();
	}

	@Test
	// Failed
	public void testWriteAvailability() {
		assertEquals(0, service.getAllAvailabilities());
		
		String tutorEmail = "hadi@gmail.com";
		
		Calendar c = Calendar.getInstance();
		c.set(2019, c.DECEMBER, 01, 12, 00);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(16, 00);
		Time endTime = new Time(c.getTimeInMillis());
		
		try {
			service.createAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List <Availability> allAvailabilities = service.getAllAvailabilities();
	
		assertEquals(1, allAvailabilities.size());
		assertEquals(startTime, allAvailabilities.get(0).getStartTime());

	}

	@Test
	// Failed
	public void testViewAvailability() {
		assertEquals(0, service.getAllAvailabilities());
		
		String tutorEmail = "hadi@gmail.com";
		
		Calendar c = Calendar.getInstance();
		c.set(2019, c.DECEMBER, 01, 12, 00);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(16, 00);
		Time endTime = new Time(c.getTimeInMillis());
		
		try {
			service.createAvailability(tutorEmail, date, startTime, endTime);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List <Availability> allAvailabilities = service.getAllAvailabilities();
	
		assertEquals(1, allAvailabilities.size());
		assertEquals(startTime, allAvailabilities.get(0).getStartTime());
		
	}

	@Test
	// Failed
	public void testWriteCourse(){
		assertEquals(0, service.getAllCourses());
		
		String description = "This is a course";
		String school = "McGill";
		String courseCode = "ECSE321";
		
		try {
			service.createCourse(description, school, courseCode);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List <Course> allCourses = service.getAllCourses();
		
		assertEquals(1, allCourses.size());
		assertEquals(school, allCourses.get(0).getSchool());

	}

	@Test
	// Failed
	public void testViewCourse(){
		assertEquals(0, service.getAllCourses());
		
		String description = "This is a course";
		String school = "McGill";
		String courseCode = "ECSE321";
		
		try {
			service.createCourse(description, school, courseCode);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List <Course> allCourses = service.getAllCourses();
		
		assertEquals(1, allCourses.size());
		assertEquals(school, allCourses.get(0).getSchool());
	}

	@Test
	// Error
	public void testWriteSession() {
		assertEquals(0, service.getAllSessions().size());
		
		Calendar c = Calendar.getInstance();
		c.set(2020,  Calendar.JANUARY, 10, 10, 00);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(18, 00);
		Time endTime = new Time(c.getTimeInMillis());
		
		String tutorEmail = "william@mcgill.ca";
		
		try {
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List<Session> allSessions = service.getAllSessions();
		
		assertEquals(1, allSessions.size());
		assertEquals(date, allSessions.get(0).getDate());
	}

	@Test
	// Error
	public void testViewSession() {
		assertEquals(0, service.getAllSessions().size());

		Calendar c = Calendar.getInstance();
		c.set(2019, Calendar.NOVEMBER, 01, 14, 00);
		Date date = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.set(20, 00);
		Time endTime = new Time(c.getTimeInMillis());

		String tutorEmail = "kathleen@gmail.com";
		
		try {
			service.createSession(tutorEmail, date, endTime, startTime);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Session> allSessions = service.getAllSessions();

		assertEquals(1, allSessions.size());
		assertEquals(startTime, allSessions.get(0).getStarTime());

	}

	@Test
	// Failed
	public void testWriteSubject(){
		assertEquals(0, service.getAllSubjects().size());

		String name = "Physics";

		try {
			service.createSubject(name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Subject> allSubjects = service.getAllSubjects();
		
		assertEquals(1, allSubjects.size());
		assertEquals(name, allSubjects.get(0).getSubjectName());

	}

	@Test

	public void testViewSubject(){
		assertEquals(0, service.getAllSubjects().size());

		String name = "Math";

		try {
			service.createSubject(name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Subject> allSubjects = service.getAllSubjects();

		assertEquals(1, allSubjects.size());
		assertEquals(name, allSubjects.get(0).getSubjectName());
	}

	@Test

	public void testWriteTutor(){
		assertEquals(0, service.getAllTutors().size());

		String name = "Oscar";

		try {
			service.createTutor("a@mcgill.com", name, "1", "3", 3);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Tutor> allPersons = service.getAllTutors();

		assertEquals(1, allPersons.size());
		assertEquals(name, allPersons.get(0).getName());
	}

	@Test

	public void testViewTutor(){
		assertEquals(0, service.getAllTutors().size());
		
		String name = "Marwan";
		String email = "marwan@mcgill.ca";
		String password = "2";
		String phoneNumber = "3";
		int hourlyRate = 5;
		
		try {
			service.createTutor(email, name, password, phoneNumber, hourlyRate);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List<Tutor> allPersons = service.getAllTutors();
		
		assertEquals(1, allPersons.size());
		assertEquals(phoneNumber, allPersons.get(0).getPhoneNumber());
	}


}
