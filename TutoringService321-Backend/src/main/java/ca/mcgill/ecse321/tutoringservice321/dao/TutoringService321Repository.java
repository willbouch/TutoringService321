package ca.mcgill.ecse321.tutoringservice321.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Course;
import ca.mcgill.ecse321.tutoringservice321.model.Subject;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;


public class TutoringService321Repository {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Availability createAvailability(Tutor tutor, Date date, Time startTime, Time endTime, int availabilityID) {
		Availability a = new Availability();
		a.setTutor(tutor);
		a.setDate(date);
		a.setStartTime(startTime);
		a.setEndTime(endTime);
		a.setAvailabilityID(availabilityID);
		entityManager.persist(a);
		return a;
	}
	
	@Transactional
	public Availability getAvailability(Integer ID) {
		return entityManager.find(Availability.class, ID);
	}
	
	@Transactional
	public Course createCourse(String description, String school, String courseCode, int courseID, Set<Subject> subjects) {
		Course c = new Course();
		c.setDescription(description);
		c.setSchool(school);
		c.setCourseCode(courseCode);
		c.setCourseID(courseID);
		c.setSubject(subjects);
		return c;
	}
	
	@Transactional
	public Course getCourse(Integer ID) {
		return entityManager.find(Course.class, ID);
	}
}
