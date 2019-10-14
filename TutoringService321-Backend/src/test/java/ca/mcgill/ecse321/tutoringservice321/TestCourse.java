package ca.mcgill.ecse321.tutoringservice321;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.project.model.*;
import ca.mcgill.ecse321.project.dao.*;
import ca.mcgill.ecse321.project.service.*;
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
	
	
	@Before
	public void setUp(){
		service.createSubject("Science");
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
	public void testCreateCourse() {

		String description = "new";
		String courseCode = "COM SCI 101";
		String id = "1000";

		List<Course> allCourses = service.getAllCourses();
		
		try {
			service.createCourse(description, courseCode, id);
			fail();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			assertEquals(1, allCourses.size());
			assertEquals(id, allCourses.get(0).getCourseID());
			assertEquals(description, allCourses.get(0).getDescription());
			assertEquals(courseCode, allCourses.get(0).getCourseCode());
			assertEquals("McGill", allCourses.get(0).getSchool().getClass());
		}
	}
	
	@Test
	public void testUpdateCourse() {

		String description = "new";
		String courseCode = "COM SCI 101";
		String id = "1000";
	
		List<Course> allCourses = service.getAllCourses();
		
		try {
			service.createCourse(description, courseCode, id);
			fail();
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			assertEquals(1, allCourses.size());
			assertEquals(id, allCourses.get(0).getCourseID());
			assertEquals(description, allCourses.get(0).getDescription());
			assertEquals(courseCode, allCourses.get(0).getCourseCode());
			assertEquals("McGill", allCourses.get(0).getSchool().getClass());
		}

		List<Course> allCourses = service.getAllCourses();

		assertEquals(1, allCourses.size());
		assertEquals(id, allCourses.get(0).getCourseID());
		assertEquals(description, allCourses.get(0).getDescription());
		assertEquals(courseName, allCourses.get(0).getCourseCode());
		assertEquals("McGill", allCourses.get(0).getUniversity().getName());
		
		description = "updated";
		courseName = "COM SCI 102";
		id = 1001;
		
		try {
			service.updateCourse(1000, description, courseName, id, 1);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		allCourses = service.getAllCourses();

		assertEquals(1, allCourses.size());
		assertEquals(id, allCourses.get(0).getCourseID());
		assertEquals(description, allCourses.get(0).getDescription());
		assertEquals(courseName, allCourses.get(0).getCourseCode());
		assertEquals("McGill", allCourses.get(0).getUniversity().getName());
	}
	
	@Test
	public void testDeleteCourse() {
		assertEquals(0, service.getAllCourses().size());

		String description = "new";
		String courseName = "COM SCI 101";
		int id = 1000;

		try {
			service.createCourse(description, courseName, id, 1);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		try {
			service.deleteCourse(id);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Course> allCourses = service.getAllCourses();

		assertEquals(0, allCourses.size());
	}
	
	@Test
	public void testCreateCourseNullDescription() {

		String description = null;
		String courseName = "COM SCI 101";
		int id = 1000;
		
		String error = null;

		try {
			service.createCourse(description, courseName, id, 1);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}

		List<Course> allCourses = service.getAllCourses();

		assertEquals(0, allCourses.size());

	}
	
	@Test
	public void testCreateCourseNullCourseName() {

		String description = "new";
		String courseName = null;
		int id = 1000;

		String error = null;
		
		try {
			service.createCourse(description, courseName, id, 1);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}

		List<Course> allCourses = service.getAllCourses();

		assertEquals(0, allCourses.size());

	}
	
	@Test
	public void testCreateCourseNullID() {

		String description = "new";
		String courseName = "COM SCI 101";
		int id = (Integer)null;
		
		String error = null;

		try {
			service.createCourse(description, courseName, id, 1);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}

		List<Course> allCourses = service.getAllCourses();

		assertEquals(0, allCourses.size());

	}

	@Test
	public void testCreateCourseNoUniversity() {

		String description = "new";
		String courseName = "COM SCI 101";
		int id = 1000;
		
		String error = null;

		try {
			service.createCourse(description, courseName, id, 2);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			error = e.getMessage();
		}

		List<Course> allCourses = service.getAllCourses();

		assertEquals(0, allCourses.size());

	}
}

}
