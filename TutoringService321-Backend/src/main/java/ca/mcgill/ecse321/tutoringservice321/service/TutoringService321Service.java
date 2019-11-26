package ca.mcgill.ecse321.tutoringservice321.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.*;

import org.aspectj.weaver.ast.Test;
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
	@Autowired
	ReviewRepository reviewRepository;
	
	private static ServiceUser loggedUser = null;
	
	public static ServiceUser getLoggedUser() {
		return loggedUser;
	}

	//====================================================================================
	//TUTOR METHODS

	@Transactional
	public Tutor createTutor(String email, String name, String password, String phoneNumber,
			int hourlyRate) {

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
		if(hourlyRate < 0) {
			throw new IllegalArgumentException("Hourly has to be a positive number.");
		}
		if(!email.matches(".{1,}@.{1,}\\..{2,3}")) {
			throw new IllegalArgumentException("The email should be in the format of <example@something.ca/com/etc.>.");
		}
		if(phoneNumber.length() != 10) {
			throw new IllegalArgumentException("Phone number has to be 10 character long.");
		}
		if(password.length() < 8) {
			throw new IllegalArgumentException("Password has to be at least 8 characters long.");
		}

		//Check if email already used
		List<Tutor> allTutors = getAllTutors();
		for(Tutor tutor : allTutors) {
			if(tutor.getEmail().equals(email)) {
				throw new IllegalArgumentException("A tutor with the same email already exists.");
			}
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
		tutor.setAvailability(new HashSet<>());
		tutor.setSession(new HashSet<>());
		tutor.setSubject(new HashSet<>());
		
		tutorRepository.save(tutor);
		return tutor;
	}

	@Transactional
	public Tutor updateTutor(String email, String name, String phoneNumber,
			int hourlyRate) {
		Tutor tutor = getTutor(email);
		if (tutor==null) {
			throw new IllegalArgumentException("The tutor with that email could not be found.");
		}
		if(name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		if(phoneNumber == null || phoneNumber.trim().length() == 0) {
			throw new IllegalArgumentException("Phone number cannot be empty.");
		}
		if(hourlyRate < 0) {
			throw new IllegalArgumentException("Hourly has to be a positive number.");
		}
		if(phoneNumber.length() != 10) {
			throw new IllegalArgumentException("Phone number has to be 10 character long.");
		}


		tutor.setEmail(email);
		tutor.setName(name);
		tutor.setPhoneNumber(phoneNumber);
		tutor.setHourlyRate(hourlyRate);
		loggedUser = tutor;
		return tutor;
	}

	@Transactional
	public Tutor changePassword(String tutorEmail, String oldPassword, String newPassword) {
		Tutor tutor = getTutor(tutorEmail);
		if(tutor==null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}
		if (!tutor.getPassword().equals(oldPassword)) {
			throw new IllegalArgumentException("That is not the correct password.");
		}
		if (newPassword==null || newPassword.trim().length()==0) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}
		if(newPassword.length() < 8) {
			throw new IllegalArgumentException("Password has to be at least 8 characters long.");
		}

		tutor.setPassword(newPassword);
		loggedUser = tutor;
		return tutor;
	}

	@Transactional
	public void deleteTutor(String email) {
		Tutor tutor = tutorRepository.findTutorByEmail(email);

		if(tutor != null) {
			tutorRepository.delete(tutor);
		}
		else {
			throw new IllegalArgumentException("Tutor could not be found.");
		}
	}

	@Transactional
	public Tutor getTutor(String email) {
		//		if(!email.matches(".{1,}@.{1,}\\..{2,3}")) {
		//			throw new IllegalArgumentException("The email should be in the format of <example@something.ca/com/etc.>.");
		//		}
		Tutor tutor = tutorRepository.findTutorByEmail(email);
		return tutor;
	}

	@Transactional
	public List<Tutor> getAllTutors() {
		return toList(tutorRepository.findAll());
	}

	//====================================================================================
	//SUBJECT METHODS

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
		subject.setCourse(new HashSet<>());
		subject.setTutor(new HashSet<>());

		subjectRepository.save(subject);
		return subject;
	}

	@Transactional
	public Subject addSubjectToTutor(String subjectName, String tutorEmail) {
		//Find the tutor
		if (tutorEmail == null) {
			throw new IllegalArgumentException("No tutor email has been specified.");
		}
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);
		
		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		//find the subject
		if (subjectName == null) {
			throw new IllegalArgumentException("No subject name has been specified.");
		}
		Subject subject = subjectRepository.findSubjectBySubjectName(subjectName);

		if(subject == null) {
			throw new IllegalArgumentException("Subject could not be found.");
		}

		subject.getTutor().add(tutor);
		
		return subject;
	}

	@Transactional
	public Subject getSubject(String name) {
		Subject subject = subjectRepository.findSubjectBySubjectName(name);
		return subject;
	}

	@Transactional
	public void deleteSubject(String name) {
		Subject subject = subjectRepository.findSubjectBySubjectName(name);

		if(subject != null) {
			subjectRepository.delete(subject);
		}
		else {
			throw new IllegalArgumentException("Subject could not be found.");
		}
	}

	@Transactional
	public void removeSubjectFromTutor(String subjectName, String tutorEmail) {
		//Find the tutor
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);

		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		//find the subject
		Subject subject = subjectRepository.findSubjectBySubjectName(subjectName);

		if(subject == null) {
			throw new IllegalArgumentException("Subject could not be found.");
		}

		tutor.getSubject().remove(subject);
	}

	@Transactional
	public List<Subject> getAllSubjects() {
		return toList(subjectRepository.findAll());
	}

	//====================================================================================
	//SESSION METHODS

	@Transactional
	public Session createSession(String tutorEmail, Date date, Time startTime, Time endTime) {

		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

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
		
		if(tutor == null) {
			throw new IllegalArgumentException("Tutor cannot be empty.");
		}
		
		if(startTime.before(Time.valueOf("9:00:00"))) {
					throw new IllegalArgumentException("Start time must be between 9 am and 9 pm.");
		}
		
		if(endTime.after(Time.valueOf("21:00:00"))) {
					throw new IllegalArgumentException("End time must be between 9 am and 9 pm.");
		}
		
		if(startTime.after(endTime)) {
					throw new IllegalArgumentException("Start time must be before End time.");
					
		}

		Session session = new Session();
		//Setting the attributes
		session.setDate(date);
		session.setStarTime(startTime);
		session.setEndTime(endTime);
		session.setIsApproved(false);
		session.setSessionID(date.hashCode()*startTime.hashCode()*endTime.hashCode());
		session.setTutor(tutor);
		session.setReview(new HashSet<>());
		tutor.getSession().add(session);

		sessionRepository.save(session);
		return session;
	}
	
	@Transactional
	public Session getSession(String tutorEmail, Date date, Time startTime, Time endTime) {	
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		//We check the set of availabilities with that date
		Set<Session> sessions = sessionRepository.findSessionByTutorAndDate(tutor, date);

		if( sessions == null) {
			return null;
		}

		for(Session session : sessions) {
			if(startTime.equals(session.getStarTime()) && endTime.equals(session.getEndTime())) {
				return session;
			}
		}
    
		return null;
	}

	@Transactional
	public Session approveSession(String tutorEmail, Date date, Time startTime, Time endTime) {

		//We first check that there is no session at that time
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);
		
		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}
		
		Set<Session> sessions = sessionRepository.findSessionByTutorAndDate(tutor, date);
		Session approvedSession=null;

		for(Session session : sessions) {
			if(session.getStarTime().equals(startTime) && session.getEndTime().equals(endTime)) {
				approvedSession=session;
			}
		}

		if(approvedSession == null) {
			throw new IllegalArgumentException("The session to approve could not be found.");
		}
		
		//We check to see if other approved sessions would overlap with that one
		//We first get all approves Sessions
		List<Session> approvedSessions = new ArrayList<>();
		for(Session session : sessions) {
			if(session.getIsApproved()) {
				approvedSessions.add(session);
			}
		}
		
		//Then we check for overlap
		for(Session aSession : approvedSessions) {
			if(endTime.compareTo(aSession.getStarTime()) >= 0 && startTime.compareTo(aSession.getStarTime()) <= 0 ||
					startTime.compareTo(aSession.getEndTime()) <= 0 && endTime.compareTo(aSession.getEndTime()) >= 0 ||
					startTime.compareTo(aSession.getStarTime()) >= 0 && endTime.compareTo(aSession.getEndTime()) <= 0) {
				throw new IllegalArgumentException("This session would overlap with an existing approved session.");
			}
		}
		
		//We then approve the session
		approvedSession.setIsApproved(true);

		return approvedSession;
	}

	@Transactional
	public void cancelSession(String tutorEmail, Date date, Time startTime, Time endTime) {

		Session session = getSession(tutorEmail, date, startTime, endTime);

		if(session != null) {
			sessionRepository.delete(session);
		}
		else {
			throw new IllegalArgumentException("Could not cancel the session.");
		}
	}

	@Transactional
	public List<Session> getAllSessions() {
		return toList(sessionRepository.findAll());
	}
	
	@Transactional
	public List<Session> getAllTutorSessions(String email) {
		Tutor tutor = getTutor(email);
		return toList(sessionRepository.findSessionByTutor(tutor));
	}

	//====================================================================================
	//AVAILABILITY METHODS

	@Transactional
	public Availability addAvailability(String tutorEmail, Date date, Time startTime, Time endTime) {
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
		if(startTime.before(Time.valueOf("9:00:00"))) {
			throw new IllegalArgumentException("Start time must be between 9 am and 9 pm.");
		}
		if(endTime.after(Time.valueOf("21:00:00"))) {
			throw new IllegalArgumentException("End time must be between 9 am and 9 pm.");
		}
		if(startTime.after(endTime)) {
			throw new IllegalArgumentException("Start time must be before End time.");
		}

		//Checking if this Availability already exists
		List<Availability> availabilities = toList(availabilityRepository.findAvailabilityByDateAndTutor(date, tutor));
		for(Availability avail :  availabilities) {
			if(endTime.compareTo(avail.getStartTime()) > 0 && startTime.compareTo(avail.getStartTime()) < 0 ||
					startTime.compareTo(avail.getEndTime()) < 0 && endTime.compareTo(avail.getEndTime()) > 0 ||
					startTime.compareTo(avail.getStartTime()) > 0 && endTime.compareTo(avail.getEndTime()) < 0) {
				throw new IllegalArgumentException("Availability conflicts with already existing availability.");
			}
		}

		Availability availability = new Availability();
		//Setting attributes
		availability.setDate(date);
		availability.setEndTime(endTime);
		availability.setStartTime(startTime);
		availability.setAvailabilityID(tutorEmail.hashCode()*date.hashCode()*startTime.hashCode()*endTime.hashCode());
		availability.setTutor(tutor);

		availabilityRepository.save(availability);
		return availability;
	}

	@Transactional
	public Availability getAvailability(String tutorEmail, Date date, Time startTime, Time endTime) {	
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		//We check the set of availabilities with that date
		Set<Availability> availabilities = availabilityRepository.findAvailabilityByDateAndTutor(date, tutor);

		if(availabilities == null) {
			return null;
		}

		for(Availability availability : availabilities) {
			if(startTime.equals(availability.getStartTime()) && endTime.equals(availability.getEndTime())) {
				return availability;
			}
		}

		return null;
	}

	@Transactional
	public void deleteAvailability(String tutorEmail, Date date, Time startTime, Time endTime) {

		Availability availability = getAvailability(tutorEmail, date, startTime, endTime);

		if(availability != null) {
			availabilityRepository.delete(availability);
		}
		else {
			throw new IllegalArgumentException("Availability could not be found.");
		}
	}

	@Transactional
	public List<Availability> getAllTutorAvailabilities(String tutorEmail) {

		List<Availability> list = toList(availabilityRepository.findAll());
		List<Availability> tutorAvailabilities = new ArrayList<Availability>();
		for(Availability availability : list) {
			if(availability.getTutor().getEmail().equals(tutorEmail)) {
				tutorAvailabilities.add(availability);
			}
		}

		return tutorAvailabilities;
	}

	@Transactional
	public Availability updateAvailability(String tutorEmail, Date oldDate, Time oldStartTime, Time oldEndTime,
			Date newDate, Time newStartTime, Time newEndTime) {

		//We first check that there is no session at that time
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);

		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		Set<Session> sessions = sessionRepository.findSessionByTutorAndDate(tutor, oldDate);
		for(Session session : sessions) {
			if(session.getStarTime().equals(oldStartTime) && session.getEndTime().equals(oldEndTime)) {
				throw new IllegalArgumentException("Already a session at that time and date.");
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
	public Tutor loginAsTutor(String email, String password) {
		//Input validation
		if(email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("Email cannot be empty.");
		}
		if(password == null || password.trim().length() == 0) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}

		List<Tutor> tutors = getAllTutors();

		Tutor foundTutor = null;
		for(Tutor tutor : tutors) {
			if(tutor.getEmail().equals(email) && tutor.getPassword().equals(password)) {
				loggedUser = tutor;
				foundTutor = tutor;
				break;
			}
		}

		if(foundTutor == null) {
			throw new IllegalArgumentException("Could not find any corresponding tutor account.");
		}

		return foundTutor;
	}

	@Transactional
	public void logout() {
		loggedUser = null;
	}
	
	@Transactional
	public ServiceUser getLoggedInUser() {
		return loggedUser;

	}

	//====================================================================================
	//COURSE METHODS

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
		course.setSubject(new HashSet<>());

		courseRepository.save(course);
		return course;
	}

	@Transactional
	public Course getCourse(String school, String courseCode) {
		Set<Course> courses = courseRepository.findCourseBySchool(school);

		if(courses == null) {
			return null;
		}

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
	public List<Course> getAllTutorCourses(String tutorEmail) {
		//Find the tutor first
		Tutor tutor = getTutor(tutorEmail);

		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		List<Subject> subjects = toList(subjectRepository.findAll());
		List<Course> tutorCourses = new ArrayList<Course>();
		for(Subject subject : subjects) {
			if(subject.getTutor().contains(tutor)) {
				for(Course course : subject.getCourse()) {
					tutorCourses.add(course);
				}
			}
		}

		return tutorCourses;
	}

	@Transactional
	public Course addCourseToSubject(String school, String courseNumber, String subjectName) {			
		// Finds the course offering
		Course foundCourse = null;
		if (school == null) {
			throw new IllegalArgumentException("No school has been specified.");
		}
		if (courseNumber == null) {
			throw new IllegalArgumentException("No course number has been specified");
		}
		
		Set<Course> courses = courseRepository.findCourseBySchool(school);
		for(Course course : courses) {
			if(course.getCourseCode().equals(courseNumber)) {
				foundCourse = course;
			}
		}

		if(foundCourse == null) {
			throw new IllegalArgumentException("The course could not be found.");
		}

		//Find the subject
		if (subjectName == null) {
			throw new IllegalArgumentException("No subject name has been specified.");
		}
		Subject subject = subjectRepository.findSubjectBySubjectName(subjectName);

		if(subject == null) {
			throw new IllegalArgumentException("Subject could not be found");
		}

		//Add the course to this subject
		subject.getCourse().add(foundCourse);
		return foundCourse;
	}

	@Transactional
	public void removeCourseFromSubject(String school, String courseNumber, String subjectName) {
		// Finds the course offering
		Course foundCourse = null;
		if (school == null) {
			throw new IllegalArgumentException("No school has be specified.");
		}
		if (courseNumber == null) {
			throw new IllegalArgumentException("No course number has been specified");
		}
		Set<Course> courses = courseRepository.findCourseBySchool(school);
		for(Course course : courses) {
			if(course.getCourseCode().equals(courseNumber)) {
				foundCourse = course;
			}
		}

		if(foundCourse == null) {
			throw new IllegalArgumentException("The course could not be found.");
		}

		//Find the subject
		if (subjectName == null) {
			throw new IllegalArgumentException("No subject name has been specified.");
		}
		Subject subject = subjectRepository.findSubjectBySubjectName(subjectName);

		if(subject == null) {
			throw new IllegalArgumentException("Subject could not be found");
		}

		subject.getCourse().remove(foundCourse);
	}

	@Transactional
	public String requestCourse(String courseCode, String tutorEmail) {
		if(courseCode == null || courseCode.trim().length() == 0) {
			throw new IllegalArgumentException("Course Code cannot be empty.");
		}
		if(tutorEmail == null || tutorEmail.trim().length() == 0) {
			throw new IllegalArgumentException("Tutor Email cannot be empty.");
		}

		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail); 

		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		String tutorName = tutor.getName();
		String email = "Dear Manager,\n"
				+ "	I would like to offer a new course, "+courseCode+"\n"
				+ " but it is not currently part of the offered courses. \n"
				+ " Could you please make it available so I could teach it?\n"
				+ " Thank you,\n"
				+ " "+tutorName;

		return email;
	}

	//====================================================================================
	//REVIEW METHODS

	@Transactional
	public Review submitTutorReview(String textualReview, String tutorEmail, Date date, Time startTime, Time endTime) {
		Review review = new Review();

		//Input validation
		if(textualReview == null || textualReview.trim().length() == 0) {
			throw new IllegalArgumentException("Review cannot be empty.");
		}
		if(tutorEmail == null || tutorEmail.trim().length() == 0) {
			throw new IllegalArgumentException("Tutor Email cannot be empty.");
		}
		if(date == null) {
			throw new IllegalArgumentException("Date cannot be empty.");
		}
		if(startTime == null) {
			throw new IllegalArgumentException("Start Time cannot be empty.");
		}
		if(endTime == null) {
			throw new IllegalArgumentException("End Time cannot be empty.");
		}

		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);

		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		Session foundSession = null;
		Set<Session> sessions = sessionRepository.findSessionByTutorAndDate(tutor, date);

		if(sessions == null) {
			throw new IllegalArgumentException("Sessions with that date and tutor could not be found.");
		}

		for(Session session : sessions) {
			if(startTime.equals(session.getStarTime()) && endTime.equals(session.getEndTime())) {
				foundSession = session;
			}
		}

		if(foundSession == null) {
			throw new IllegalArgumentException("Session could not be found.");
		}

		//Setting the attributes
		review.setAuthorEmail(tutorEmail);
		review.setTextualReview(textualReview);
		review.setSession(foundSession);
		review.setReviewID(tutorEmail.hashCode()*textualReview.hashCode());
		review.setRating(-1); //Tutor does not give any rating, so his review has a -1 rating
		reviewRepository.save(review);

		return review;
	}

	@Transactional
	public List<Review> getAllTutorReviews(String tutorEmail) {
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);

		if(tutor == null) {
			throw new IllegalArgumentException("Tutor could not be found.");
		}

		List<Review> tutorReviews = new ArrayList<Review>();
		Set<Session> sessions = sessionRepository.findSessionByTutor(tutor);
		for(Session session : sessions) {
			Set<Review> reviews = session.getReview();
			for(Review review : reviews) {
				if(!(review.getAuthorEmail().equals(tutorEmail))) {
					tutorReviews.add(review);
				}
			}
		}

		return tutorReviews;
	}

	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}

	//====================================================================================

	//Helper method provided in EventRegistration
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		if(iterable != null) {
			for (T t : iterable) {
				resultList.add(t);
			}
		}
		return resultList;
	}

}