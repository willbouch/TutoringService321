package ca.mcgill.ecse321.tutoringservice321.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringservice321.dao.*;
import ca.mcgill.ecse321.tutoringservice321.model.*;

@Service
public class TutoringService321Service {
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

	@Transactional
	public Tutor createTutor(String email, String name, String password, String phoneNumber,
			int hourlyRate) {
		Tutor tutor = new Tutor();

		//Input validation
		if(email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Email cannot be empty.");
		}
		if(name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		if(password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}
		if(phoneNumber == null || phoneNumber.trim().length() == 0) {
			throw new IllegalArgumentException("Phone number cannot be empty.");
		}
		if(hourlyRate <= 0) {
			throw new IllegalArgumentException("Hourly has to be a positive number.");
		}

		//Setting the attributes
		//Note that rating starts at -1 as a flag for "no rating yet"
		tutor.setEmail(email);
		tutor.setName(name);
		tutor.setPassword(password);
		tutor.setPhoneNumber(phoneNumber);
		tutor.setHourlyRate(hourlyRate);
		tutor.setRating(-1);
		tutor.setUserID(name.hashCode()*password.hashCode()*email.hashCode());

		tutorRepository.save(tutor);
		return tutor;
	}

	@Transactional
	public Tutor getTutor(String email) {
		Tutor tutor = tutorRepository.findTutorByEmail(email);
		return tutor;
	}

	@Transactional
	public List<Tutor> getAllTutors() {
		return toList(tutorRepository.findAll());
	}
	
	@Transactional
	public Subject createSubject(String name) {
		Subject subject = new Subject();

		//Input validation
		if(name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("The name cannot be empty.");
		}

		//Setting the attributes
		subject.setSubjectName(name);
		subject.setSubjectID(name.hashCode());

		subjectRepository.save(subject);
		return subject;
	}

	@Transactional
	public Subject getSubject(String name) {
		Subject subject = subjectRepository.findSubjectBySubjectName(name);
		return subject;
	}

	@Transactional
	public List<Subject> getAllSubjects() {
		return toList(subjectRepository.findAll());
	}
	
	@Transactional
	public Session createSession(String tutorEmail, Date date, Time endTime, Time startTime) {
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		Session session = new Session();

		//Input validation
		if(date == null) {
			throw new IllegalArgumentException("Date cannot be empty.");
		}
		if(endTime == null) {
			throw new IllegalArgumentException("End time cannot be empty.");
		}
		if(startTime == null) {
			throw new IllegalArgumentException("Start time cannot be empty.");
		}

		//Setting the attributes
		session.setDate(date);
		session.setStarTime(startTime);
		session.setEndTime(endTime);
		session.setSessionID(date.hashCode()*startTime.hashCode()*endTime.hashCode());
		session.setTutor(tutor);
		tutor.getSession().add(session);

		sessionRepository.save(session);
		return session;
	}

	@Transactional
	public Session getSession(Date date, String tutorEmail) {
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		Set<Session> sessions = sessionRepository.findSessionByDate(date);
		for(Session session : sessions) {
			if(tutor.equals(session.getTutor())) return session;
		}

		//Not suppose to happen
		return null;
	}

	@Transactional
	public List<Session> getAllSessions() {
		return toList(sessionRepository.findAll());
	}
	
	@Transactional
	public Course createCourse(String description, String school, String courseCode) {
		Course course = new Course();

		//Input validation
		if(description == null || description.trim().length() == 0) {
			throw new IllegalArgumentException("Description cannot be empty.");
		}
		if(school == null || school.trim().length() == 0) {
			throw new IllegalArgumentException("School name cannot be empty.");
		}
		if(courseCode == null || courseCode.trim().length() == 0) {
			throw new IllegalArgumentException("Course code cannot be empty.");
		}

		//Setting the attributes
		course.setCourseCode(courseCode);
		course.setDescription(description);
		course.setSchool(school);
		course.setCourseID(courseCode.hashCode()*school.hashCode());

		courseRepository.save(course);
		return course;
	}

	@Transactional
	public Course getCourse(String school, String courseCode) {
		Set<Course> courses = courseRepository.findCourseBySchool(school);
		for(Course course : courses) {
			if(courseCode.equals(course.getCourseCode())) return course;
		}

		//Not suppose to happen
		return null;
	}

	@Transactional
	public List<Course> getAllCourses() {
		return toList(courseRepository.findAll());
	}
	
	@Transactional
	public Availability createAvailability(String tutorEmail, Date date, Time startTime,
			Time endTime) {
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		Availability availability =  new Availability();

		//Input validation
		if(date == null) {
			throw new IllegalArgumentException("Date cannot be empty.");
		}
		if(endTime == null) {
			throw new IllegalArgumentException("End time cannot be empty.");
		}
		if(startTime == null) {
			throw new IllegalArgumentException("Start time cannot be empty.");
		}

		//Setting attributes
		availability.setDate(date);
		availability.setEndTime(endTime);
		availability.setStartTime(startTime);
		availability.setAvailabilityID(date.hashCode()*startTime.hashCode()*endTime.hashCode());
		availability.setTutor(tutor);
		tutor.getAvailability().add(availability);

		availabilityRepository.save(availability);
		return availability;
	}

	public Availability getAvailability(Date date, String tutorEmail) {
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		Set<Availability> availabilities = availabilityRepository.findAvailabilityByDate(date);
		for(Availability availability : availabilities) {
			if(tutor.equals(availability.getTutor())) return availability;
		}

		//Not suppose to happen
		return null;
	}
	
	@Transactional
	public List<Availability> getAllAvailabilities() {
		return toList(availabilityRepository.findAll());
	}
	
	//Helper method provided in EventRegistration
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
