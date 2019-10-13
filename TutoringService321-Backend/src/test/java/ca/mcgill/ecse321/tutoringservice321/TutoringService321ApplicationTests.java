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
import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Subject;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;

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

	public void testWriteAvailability() {

	}

	@Test

	public void testViewAvailability() {
	}

	@Test

	public void testWriteCourse(){

	}

	@Test

	public void testViewCourse(){

	}

	// ... other tests

	@Test

	public void testWriteServiceUser(){

	}

	@Test

	public void testViewServiceUser(){

	}

	@Test

	public void testWriteSession() {

	}

	@Test

	public void testViewSession() {

	}

	@Test

	public void testWriteStudent(){

	}
	@Test

	public void testViewStudent(){

	}

	@Test

	public void testWriteSubject(){

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

	}


}
