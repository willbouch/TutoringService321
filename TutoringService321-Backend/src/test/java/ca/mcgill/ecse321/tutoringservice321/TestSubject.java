package ca.mcgill.ecse321.tutoringservice321;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.fail;

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


public class TestSubject {
	@RunWith(SpringRunner.class)
	@SpringBootTest
	
	public class tutorTest{
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
	
	public void clearDatabase() {
		tutorRepository.deleteAll();
		availabilityRepository.deleteAll();
		sessionRepository.deleteAll();
		courseRepository.deleteAll();
		subjectRepository.deleteAll();
	}
	
	@Test
	public void testCreateSubject() {

		String subjectName = "aName";
		

		try {
			List<Subject> allSubjects = service.getAllSubjects();
			
			service.createSubject(subjectName);
			
			assertEquals(1, allSubjects.size());
			assertEquals(subjectName, allSubjects.get(0).getSubjectName());
			
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
		}
	}
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
	
	}
}
