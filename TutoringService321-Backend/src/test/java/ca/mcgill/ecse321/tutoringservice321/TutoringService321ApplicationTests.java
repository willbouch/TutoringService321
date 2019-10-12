package ca.mcgill.ecse321.tutoringservice321;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice321.dao.AvailabilityRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutoringService321Repository;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;

	@RunWith(SpringRunner.class)
	@SpringBootTest
	public class TutoringService321ApplicationTests {
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
			assertEquals(0, availability.getDate());
	
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
		    
	    }
	    
	    @Test
	    
	    public void testWriteTutor(){
		    
	    }
	    
	    @Test
	    
	    public void testViewTutor(){
		    
	    }
	    
	    
}
	   
	    
	    
	    