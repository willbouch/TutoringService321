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

public class TestTutor {
	
	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class TutorTest {
		
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
		
		

		@Before
		public void setUp(){
			service.createTutor("aName", "test.tester@mcgill.ca", "2424", "5145555555", 0);
		}

		@After
		public void clearDatabase() {
			tutorRepository.deleteAll();
			availabilityRepository.deleteAll();
			sessionRepository.deleteAll();
			courseRepository.deleteAll();
			subjectRepository.deleteAll();
		}
		
		@Test
		public void testCreateTutor() {

			String username = "aName";
			String email = "test.tester@mcgill.ca";
			String password  = "Ilovedogs";
			String phoneNumber = "5145555555";
			int hourlyRate = 25;

			try {
				service.createTutor(username, email, password, phoneNumber, hourlyRate);
			} catch (IllegalArgumentException e) {
				// Check that no error occurred
				fail();
			}

			List<Tutor> allTutors = service.getAllTutors();

			assertEquals(1, allTutors.size());
			assertEquals(username, allTutors.get(0).getUserID());
			assertEquals(password, allTutors.get(0).getPassword());
			assertEquals(hourlyRate, allTutors.get(0).getHourlyRate(), 0.05);
			assertEquals(email, allTutors.get(0).getEmail());
		}
		
		@Test
		public void testUpdateTutor() {

			String username = "aName";
			String email = "test.tester@mcgill.ca";
			String password  = "Ilovedogs";
			String phoneNumber = "5145555555";
			int hourlyRate = 25;
			
			try {
				service.createTutor(username, email, password, phoneNumber, hourlyRate);
			} catch (IllegalArgumentException e) {
				// Check that no error occurred
				fail();
			}

			List<Tutor> allTutors = service.getAllTutors();

			assertEquals(1, allTutors.size());
			
			String newUsername = "anUpdatedName";
			String newEmail = "test.newtester@mcgill.ca";
			String newPassword  = "Ichangedmymind,ilovecats";
			String newPhoneNumber = "5147777777";
			int newHourlyRate = 30;
			
			try {
				service.createTutor(newUsername, newEmail, newPassword, newPhoneNumber, newHourlyRate);
			} catch (IllegalArgumentException e) {
				// Check that no error occurred
				fail();
			}
			
			assertEquals(username, allTutors.get(0).getUserID());
			assertEquals(password, allTutors.get(0).getPassword());
			assertEquals(newHourlyRate, allTutors.get(0).getHourlyRate(), 30);
			
		}
		
		
		@Test
		public void testCreateTutorNullUsername() {

			String username = null;
			String email = "test.tester@mcgill.ca";
			String password  = "Ilovedogs";
			String phoneNumber = "5145555555";
			int hourlyRate = 25;
			

			try {
				service.createTutor(username, email, password, phoneNumber, hourlyRate);
			} catch (IllegalArgumentException e) {
				// Check that no error occurred
				fail();
			}

			List<Tutor> allTutors = service.getAllTutors();

			assertEquals(0, allTutors.size());
		}
		
		@Test
		public void testCreateTutorNullPassword() {

			String username = "aName";
			String email = "test.tester@mcgill.ca";
			String password  = null;
			String phoneNumber = "5145555555";
			int hourlyRate = 25;
			

			try {
				service.createTutor(username, email, password, phoneNumber, hourlyRate);
			} catch (IllegalArgumentException e) {
				// Check that no error occurred
				fail();
			}

			List<Tutor> allTutors = service.getAllTutors();

			assertEquals(0, allTutors.size());
		}
		
		@Test
		public void testCreateTutorWrongEmail() {

			/*String username = "email";
			String password = "test";
			double hr  = 12;
			int exp = 3;*/
			
			String username = null;
			String password  = "Ilovedogs";
			String phoneNumber = "5145555555";
			int hourlyRate = 25;
			

			try {
				service.createTutor(username, "wrong email", password, phoneNumber, hourlyRate);
			} catch (IllegalArgumentException e) {
				// Check that no error occurred
				fail();
			}

			List<Tutor> allTutors = service.getAllTutors();

			assertEquals(0, allTutors.size());
		}
	}
	

}
