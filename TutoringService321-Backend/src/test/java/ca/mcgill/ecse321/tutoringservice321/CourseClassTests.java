package ca.mcgill.ecse321.tutoringservice321;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;


import ca.mcgill.ecse321.tutoringservice321.dao.AvailabilityRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SessionRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringservice321.dao.SubjectRepository;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;
import ca.mcgill.ecse321.tutoringservice321.model.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseClassTests {
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

	@Test
	public void testWriteCourse(){
		assertEquals(0, service.getAllCourses().size());
		
		String description = "This is a course";
		String school = "McGill";
		String courseCode = "ECSE321";
		
		Course course = null;
		try {
			course = service.createCourse(description, school, courseCode);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List <Course> allCourses = service.getAllCourses();
		
		assertEquals(1, allCourses.size());
		assertEquals(school, allCourses.get(0).getSchool());
		assertEquals(course, service.getCourse(school, courseCode));
	}

	@Test
	public void testViewCourse(){
		assertEquals(0, service.getAllCourses().size());
		
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
}
