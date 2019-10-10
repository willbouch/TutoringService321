package ca.mcgill.ecse321.tutoringservice321.service;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringservice321.dao.*;
import ca.mcgill.ecse321.tutoringservice321.model.*;
public class TutoringService321Service {

	@Autowired
	TutorRepository tutorRepository;
	@Autowired
	AvailabilityRepository availabilityRepository;
	@Autowired
	SessionRepository sessionRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	SubjectRepository subjectRepository;
	@Autowired
	StudentRepository studentRepository;
	
	
	@Transactional
	public Availability createAvailability(Tutor tutor, Date date, Time startTime, Time endTime, int availabilityID) {
		Availability a = new Availability();
		a.setTutor(tutor);
		a.setDate(date);
		a.setStartTime(startTime);
		a.setEndTime(endTime);
		a.setAvailabilityID(availabilityID);
		availabilityRepository.save(a);
		return a;
	}

	@Transactional
	public Optional<Availability> getAvailability(Integer ID) {
		Optional<Availability> a=availabilityRepository.findById(ID);
		return a;
	}

	@Transactional
	public Course createCourse(String description, String school, String courseCode, int courseID,
			Set<Subject> subjects) {
		Course c = new Course();
		c.setDescription(description);
		c.setSchool(school);
		c.setCourseCode(courseCode);
		c.setCourseID(courseID);
		c.setSubject(subjects);
		courseRepository.save(c);
		return c;
	}

	@Transactional
	public Optional<Course> getCourse(Integer ID) {
		Optional<Course> c=courseRepository.findById(ID);
		return c;
	}
}
