package ca.mcgill.ecse321.tutoringservice321;


import org.junit.Test;

import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringservice321.dao.AvailabilityRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.model.Course;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

@RunWith(SpringRunner.class)
@SpringBootTest

public class TestCourse {

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
	public void testCreateCourse() {

		String description = "new";
		String courseCode = "ECSE 321";
		String id = "1000";
		
		try {
			List<Course> allCourses = service.getAllCourses();
			
			service.createCourse(description, courseCode, id);
			
			assertEquals(1, allCourses.size());
			assertEquals(id, allCourses.get(0).getCourseID());
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
		String id = "1000";
		

		try {
			List<Course> allCourses = service.getAllCourses();
			
			service.createCourse(description, courseCode, id);
			
			assertEquals(0, allCourses.size());
			
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}


	}
	
	@Test
	public void testCreateCourseNullCourseName() {

		String description = "new";
		String courseCode = null;
		String id = "1000";

		
		try {
		
			List<Course> allCourses = service.getAllCourses();
			
			service.createCourse(description, courseCode, id);
			
			assertEquals(0, allCourses.size());
			
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

	}
	
	@Test
	public void testCreateCourseNullID() {

		String description = "new";
		String courseCode = "COM SCI 101";
		String id = null;
		

		try {
			List<Course> allCourses = service.getAllCourses();
			
			service.createCourse(description, courseCode, id);
			
			assertEquals(0, allCourses.size());
			
		} catch (IllegalArgumentException e){
			fail();
		}

	}
}

