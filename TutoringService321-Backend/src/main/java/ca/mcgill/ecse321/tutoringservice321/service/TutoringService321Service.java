package ca.mcgill.ecse321.tutoringservice321.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutoringservice321.TutoringService321Application;
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
		if(hourlyRate > 0) {
			throw new IllegalArgumentException("Hourly has to be a positive number.");
		}
		Tutor tutor = new Tutor();

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
	
	//====================================================================================
	//SESSION METHODS

	@Transactional
	public Session createSession(String tutorEmail, Date date, Time startTime, Time endTime) {
		if(TutoringService321Application.getLoggedUser() == null || !(TutoringService321Application.getLoggedUser() instanceof Tutor)) {
			throw new IllegalArgumentException("A tutor must be logged in to perform this operation");
		}
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		Session session = new Session();

		//Input validation
		if(date == null) {
			throw new IllegalArgumentException("Date cannot be empty.");
		}
		
		if(startTime == null) {
			throw new IllegalArgumentException("Start time cannot be empty.");
		}
		
		if(endTime == null) {
			throw new IllegalArgumentException("End time cannot be empty.");
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
	public Session getSession(String tutorEmail, Date date, Time startTime, Time endTime) {
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		Set<Session> sessions = sessionRepository.findSessionByDate(date);
		for(Session session : sessions) {
			if(tutor.equals(session.getTutor()) && startTime.equals(session.getStarTime()) && endTime.equals(session.getEndTime())) {
				return session;
			}
		}
		
		//Not suppose to happen
		return null;
	}
	
	
	@Transactional
	public Session approveSession(String tutorEmail, Date requestedDate, Time qStartTime, Time qEndTime,
			Date confirmedDate, Time cStartTime, Time cEndTime) {
		if(TutoringService321Application.getLoggedUser() == null || !(TutoringService321Application.getLoggedUser() instanceof Tutor)) {
			throw new IllegalArgumentException("A tutor must be logged in to perform this operation");
		}
		
		//We first check that there is no session at that time
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);
		Set<Session> sessions = sessionRepository.findSessionByTutorAndDate(tutor, requestedDate);
		for(Session session : sessions) {
			if(session.getStarTime().equals(qStartTime) && session.getEndTime().equals(qEndTime)) {
				throw new IllegalArgumentException("A session has aready been booked for that time and date.");
			}
		}
		//We then add the new one
		Session session = createSession(tutorEmail, confirmedDate, cStartTime, cEndTime);

		return session;
	}
	
	@Transactional
	public void cancelSession(String tutorEmail, Date date, Time startTime, Time endTime) {
		if(TutoringService321Application.getLoggedUser() == null || !(TutoringService321Application.getLoggedUser() instanceof Tutor)) {
			throw new IllegalArgumentException("A tutor must be logged in to perform this operation");
		}
		
		Session session = getSession(tutorEmail, date, startTime, endTime);

		if(session != null) {
			sessionRepository.delete(session);
		}
	}

	@Transactional
	public List<Session> getAllSessions(String tutorEmail) {
		//Find the tutor first
				Tutor tutor = getTutor(tutorEmail);

				if(tutor == null) {
					throw new IllegalArgumentException("There is no such Tutor.");
				}
		return toList(sessionRepository.findAll());
	}
 
	// ===============================================================================================
	
	
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

	//====================================================================================
	//AVAILABILITY METHODS

	@Transactional
	public Availability addAvailability(String tutorEmail, Date date, Time startTime, Time endTime) {
		if(TutoringService321Application.getLoggedUser() == null || !(TutoringService321Application.getLoggedUser() instanceof Tutor)) {
			throw new IllegalArgumentException("A tutor must be logged in to perform this operation");
		}
		
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

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
		if(tutor == null) {
			throw new IllegalArgumentException("Tutor cannot be empty.");
		}

		//Checking if this Availability already exists
		List<Availability> availabilities = getAllTutorAvailabilities(tutorEmail);
		for(Availability avail :  availabilities) {
			if(avail.getDate().equals(date) && avail.getStartTime().equals(startTime) && avail.getEndTime().equals(endTime)) {
				throw new IllegalArgumentException("Availability already exists.");
			}
		}

		Availability availability = new Availability();
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

	@Transactional
	public Availability getAvailability(String tutorEmail, Date date, Time startTime, Time endTime) {	
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		//We check the set of availabilities with that date
		Set<Availability> availabilities = availabilityRepository.findAvailabilityByDate(date);
		for(Availability availability : availabilities) {
			if(tutor.equals(availability.getTutor()) && startTime.equals(availability.getStartTime()) && endTime.equals(availability.getEndTime())) {
				return availability;
			}
		}

		return null;
	}

	@Transactional
	public void deleteAvailability(String tutorEmail, Date date, Time startTime, Time endTime) {
		if(TutoringService321Application.getLoggedUser() == null || !(TutoringService321Application.getLoggedUser() instanceof Tutor)) {
			throw new IllegalArgumentException("A tutor must be logged in to perform this operation");
		}
		
		Availability availability = getAvailability(tutorEmail, date, startTime, endTime);

		if(availability != null) {
			availabilityRepository.delete(availability);
		}
	}

	@Transactional
	public List<Availability> getAllTutorAvailabilities(String tutorEmail) {
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		if(tutor == null) {
			throw new IllegalArgumentException("There is no such Tutor.");
		}

		List<Availability> list = toList(availabilityRepository.findAll());
		List<Availability> tutorAvailabilities = new ArrayList<Availability>();
		for(Availability availability : list) {
			if(availability.getTutor().equals(tutor)) {
				tutorAvailabilities.add(availability);
			}
		}

		return toList(tutor.getAvailability());
	}

	@Transactional
	public Availability updateAvailability(String tutorEmail, Date oldDate, Time oldStartTime, Time oldEndTime,
			Date newDate, Time newStartTime, Time newEndTime) {
		if(TutoringService321Application.getLoggedUser() == null || !(TutoringService321Application.getLoggedUser() instanceof Tutor)) {
			throw new IllegalArgumentException("A tutor must be logged in to perform this operation");
		}
		
		//We first check that there is no session at that time
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);
		Set<Session> sessions = sessionRepository.findSessionByTutorAndDate(tutor, oldDate);
		for(Session session : sessions) {
			if(session.getStarTime().equals(oldStartTime) && session.getEndTime().equals(oldEndTime)) {
				throw new IllegalArgumentException("Already an availability at that time and date.");
			}
		}

		//We first delete the old availability
		deleteAvailability(tutorEmail, oldDate, oldStartTime, oldEndTime);

		//We then add the new one
		Availability availability = addAvailability(tutorEmail, newDate, newStartTime, newEndTime);

		return availability;
	}

	//====================================================================================
	//LOGIN-LOGOUT METHODS

	@Transactional
	public void loginAsTutor(String email, String password) {
		//Input validation
		if(email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Email cannot be empty.");
		}
		if(password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}

		List<Tutor> tutors = getAllTutors();

		for(Tutor tutor : tutors) {
			if(tutor.getEmail().equals(email) && tutor.getEmail().equals(password)) {
				TutoringService321Application.setLoggedUser(tutor);
			}
		}
	}

	@Transactional
	public void logout() {
		TutoringService321Application.setLoggedUser(null);
	}
	
	//====================================================================================

	//Helper method provided in EventRegistration
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
