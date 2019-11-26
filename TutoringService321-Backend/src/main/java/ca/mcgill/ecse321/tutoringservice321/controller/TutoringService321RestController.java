package ca.mcgill.ecse321.tutoringservice321.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.mcgill.ecse321.tutoringservice321.TutoringService321Application;
import ca.mcgill.ecse321.tutoringservice321.dto.AvailabilityDto;
import ca.mcgill.ecse321.tutoringservice321.dto.SessionDto;
import ca.mcgill.ecse321.tutoringservice321.dto.SubjectDto;
import ca.mcgill.ecse321.tutoringservice321.dto.TutorDto;
import ca.mcgill.ecse321.tutoringservice321.dto.CourseDto;
import ca.mcgill.ecse321.tutoringservice321.dto.ReviewDto;
import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.ServiceUser;
import ca.mcgill.ecse321.tutoringservice321.model.Session;
import ca.mcgill.ecse321.tutoringservice321.model.Subject;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.model.Course;
import ca.mcgill.ecse321.tutoringservice321.model.Review;
import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

@CrossOrigin(origins = "*")
@RestController
public class TutoringService321RestController {

	@Autowired
	private TutoringService321Service service;

	@PostMapping(value = {"/login/{tutorEmail}", "/login/{tutorEmail}/"})
	public void tutorLogin(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam String password) {
		service.loginAsTutor(tutorEmail, password);
	}
	
	@PutMapping(value = {"/logout", "/logout/"})
	public void logout() {
		service.logout();
	}
	
	@GetMapping(value = {"/user", "/user/"})
	public TutorDto getLoggedTutor() {
		return converToDto((Tutor)service.getLoggedInUser());
	}
	
	//====================================================================================
	//AVAILABILITY METHODS

	@PostMapping(value = {"/availabilities/{tutorEmail}", "/availabilities/{tutorEmail}/"})
	public AvailabilityDto addAvailability(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {

		Availability availability = service.addAvailability(tutorEmail, date, Time.valueOf(startTime), Time.valueOf(endTime));
//checking
		return converToDto(availability);
	}

	@GetMapping(value = {"/availabilities/{tutorEmail}", "/availabilities/{tutorEmail}/"})
	public List<AvailabilityDto> getAllTutorAvailabilities(@PathVariable("tutorEmail") String tutorEmail) {
		List<AvailabilityDto> dtos = new ArrayList<AvailabilityDto>();
		for(Availability availability : service.getAllTutorAvailabilities(tutorEmail)) {
			dtos.add(converToDto(availability));
		}
		return dtos;
	}
	
	@DeleteMapping(value = {"/availabilities/{tutorEmail}", "/availabilities/{tutorEmail}/"})
	public List<AvailabilityDto> deleteAvailability(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		service.deleteAvailability(tutorEmail, date, Time.valueOf(startTime), Time.valueOf(endTime));

		List<AvailabilityDto> dtos = new ArrayList<AvailabilityDto>();
		for(Availability availability : service.getAllTutorAvailabilities(tutorEmail)) {
			dtos.add(converToDto(availability));
		}

		return dtos;
	}

	@PutMapping(value = {"/availabilities/{tutorEmail}", "/availabilities/{tutorEmail}/"})
	public AvailabilityDto updateAvailability(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date oldDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime oldStartTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime oldEndTime,
			@RequestParam Date newDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime newStartTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime newEndTime) {

		Availability availability = service.updateAvailability(tutorEmail, oldDate, Time.valueOf(oldStartTime), Time.valueOf(oldEndTime), newDate, Time.valueOf(newStartTime), Time.valueOf(newEndTime));

		return converToDto(availability);
	}

	private AvailabilityDto converToDto(Availability availability) {
		if(availability == null) {
			throw new IllegalArgumentException("There is no such Availability.");
		}

		AvailabilityDto dto = new AvailabilityDto(availability.getDate(), availability.getStartTime(), availability.getEndTime());
		return dto;
	}

	//====================================================================================
	//TUTOR METHODS

	@PostMapping(value = {"/register/{tutorEmail}", "/register/{tutorEmail}/"})
	public TutorDto registerTutor(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam String name,
			@RequestParam String password, 
			@RequestParam String phoneNumber,
			@RequestParam int hourlyRate){

		Tutor tutor = service.createTutor(tutorEmail, name, password, phoneNumber, hourlyRate);

		return converToDto(tutor);
	}

	@GetMapping(value = {"/tutors/{tutorEmail}", "/tutors/{tutorEmail}/"})
	public TutorDto getTutor(@PathVariable("tutorEmail") String tutorEmail) {
		Tutor tutor = service.getTutor(tutorEmail);

		return converToDto(tutor);
	}

	@PutMapping(value = {"/tutors/profile/{tutorEmail}", "/tutors/profile/{tutorEmail}/"})
	public TutorDto updateProfile(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam String name, 
			@RequestParam String phoneNumber,
			@RequestParam int hourlyRate){

		Tutor tutor = service.updateTutor(tutorEmail, name, phoneNumber, hourlyRate);

		return converToDto(tutor);
	}

	@PutMapping(value = {"/tutors/password/{tutorEmail}", "/tutors/password{tutorEmail}/"})
	public TutorDto changePassword(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam String oldPassword,
			@RequestParam String newPassword){

		Tutor tutor = service.changePassword(tutorEmail,oldPassword, newPassword);

		return converToDto(tutor);
	}

	@GetMapping(value = {"/tutors", "/tutors/"})
	public List<TutorDto> getTutor() {
		List<TutorDto> dtos = new ArrayList<TutorDto>();
		for (Tutor tutor : service.getAllTutors()) {
			dtos.add(converToDto(tutor));
		}
		return dtos;
	}
	
	private TutorDto converToDto(Tutor tutor) {
		if(tutor == null) {
			throw new IllegalArgumentException("There is no such Tutor.");
		}

		Set<SubjectDto> subjectsDto = new HashSet<SubjectDto>();
		for(Subject subject: tutor.getSubject()) {
			subjectsDto.add(converToDto(subject));
		}

		Set<SessionDto> sessionsDto = new HashSet<SessionDto>();
		for(Session session: tutor.getSession()) {
			sessionsDto.add(converToDto(session));
		}

		Set<AvailabilityDto> availabilitiesDto = new HashSet<AvailabilityDto>();
		for(Availability availability: tutor.getAvailability()) {
			availabilitiesDto.add(converToDto(availability));
		}

		TutorDto dto = new TutorDto(tutor.getEmail(), tutor.getName(), tutor.getPassword(), tutor.getPhoneNumber(), availabilitiesDto, subjectsDto, sessionsDto, tutor.getHourlyRate(), tutor.getRating());
		return dto;
	}

	//====================================================================================
	//COURSE METHODS

	@GetMapping(value = {"/courses/{courseCode}", "/courses/{courseCode}/"})
	public CourseDto getCourse(@PathVariable("courseCode") String courseCode, 
			@RequestParam String school) {
		Course course = service.getCourse(school, courseCode);

		return converToDto(course);
	}

	@GetMapping(value = {"/courses", "/courses/"})
	public List<CourseDto> getAllCourses() {
		List<CourseDto> dtos = new ArrayList<CourseDto>();
		for (Course course : service.getAllCourses()) {
			dtos.add(converToDto(course));
		}
		return dtos;
	}

	@PostMapping(value = {"/courses/subject/{courseCode}", "/courses/subject/{courseCode}/"})
	public CourseDto addCourseToSubject(@PathVariable("courseCode") String courseCode, 
			@RequestParam String school, 
			@RequestParam String subjectName) {
		Course course = service.addCourseToSubject(school, courseCode, subjectName);

		return converToDto(course);
	}

	@DeleteMapping(value = {"/courses/{courseCode}", "/courses/{courseCode}/"})
	public List<CourseDto> removeCourseFromSubject(@PathVariable("courseCode") String courseCode, 
			@RequestParam String school, 
			@RequestParam String subjectName) {
		service.removeCourseFromSubject(school, courseCode, subjectName);

		List<CourseDto> dtos = new ArrayList<CourseDto>();
		for(Course course : service.getAllCourses()) {
			dtos.add(converToDto(course));
		}

		return dtos;
	}

	@GetMapping(value = {"/courses/tutor/{tutorEmail}", "/courses/tutor/{tutorEmail}/"})
	public List<CourseDto> getTutorCourses(@PathVariable("tutorEmail") String tutorEmail) {
		List<Course> tutorCourses = service.getAllTutorCourses(tutorEmail);

		List<CourseDto> dtos = new ArrayList<CourseDto>();
		for(Course course : tutorCourses) {
			dtos.add(converToDto(course));
		}

		return dtos;
	}

	@PostMapping(value = {"/courses/{courseCode}", "/courses/{courseCode}/"})
	public CourseDto createCourse(@PathVariable("courseCode") String courseCode, 
			@RequestParam String school,
			@RequestParam String description) {
		Course course = service.createCourse(description, school, courseCode);

		return converToDto(course);
	}

	private CourseDto converToDto(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("There is no such course.");
		}

		CourseDto dto = new CourseDto(course.getDescription(), course.getSchool(), course.getCourseCode());
		return dto;
	}

	//====================================================================================
	//SUBJECT METHODS

	@PostMapping(value = {"/subjects/{name}", "/subjects/{name}/"})
	public SubjectDto createSubject(@PathVariable("name") String name) {
		Subject subject = service.createSubject(name);

		return converToDto(subject);
	}

	@PostMapping(value = {"/subjects/tutor/{name}", "/subjects/tutor/{name}/"})
	public SubjectDto addSubjectToTutor(@PathVariable("name") String name,
			@RequestParam String tutorEmail) {
		Subject subject = service.addSubjectToTutor(name, tutorEmail);

		return converToDto(subject);
	}
	
	@GetMapping(value = {"/subjects/{name}", "/subjects/{name}/"})
	public SubjectDto getSubject(@PathVariable("name") String name) {
		Subject subject = service.getSubject(name);

		return converToDto(subject);
	}
	
	@GetMapping(value = {"/subjects", "/subjects/"})
	public List<SubjectDto> getAllSubjects() {
		List<SubjectDto> dtos = new ArrayList<SubjectDto>();
		for (Subject subject : service.getAllSubjects()) {
			dtos.add(converToDto(subject));
		}
		return dtos;
	}
	
	private SubjectDto converToDto(Subject subject) {
		if (subject == null) {
			throw new IllegalArgumentException("There is no such subject.");
		}

		Set<CourseDto> courses = new HashSet<CourseDto>();
		for(Course course : subject.getCourse()) {
			courses.add(converToDto(course));
		}

		SubjectDto dto = new SubjectDto(subject.getSubjectName(), courses);
		return dto;
	}
	
	//====================================================================================
	//SESSION METHODS

	@PostMapping(value = {"/sessions/{tutorEmail}", "/sessions/{tutorEmail}/"})
	public SessionDto createSession(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {

		Session session= service.createSession(tutorEmail, date, Time.valueOf(startTime), Time.valueOf(endTime));

		return converToDto(session);
	}

	@GetMapping(value = {"/sessions/{tutorEmail}", "/sessions/{tutorEmail}/"})
	public List<SessionDto> getAllTutorSessions(@PathVariable("tutorEmail") String tutorEmail) {
		List<SessionDto> dtos = new ArrayList<SessionDto>();
		for(Session session : service.getAllTutorSessions(tutorEmail)) {
			dtos.add(converToDto(session));
		}

		return dtos;
	}

	@PutMapping(value = {"/sessions/{tutorEmail}", "/sessions/{tutorEmail}/"})
	public SessionDto approveSession(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date requestedDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime qStartTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime qEndTime
		) {
		Session session= service.approveSession(tutorEmail, requestedDate, Time.valueOf(qStartTime), Time.valueOf(qEndTime));
		return converToDto(session);
	}

	@DeleteMapping(value = {"/sessions/{tutorEmail}", "/sessions/{tutorEmail}/"})
	public List<SessionDto> cancelSession(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		service.cancelSession(tutorEmail, date, Time.valueOf(startTime), Time.valueOf(endTime));

		List<SessionDto> dtos = new ArrayList<SessionDto>();
		for(Session session : service.getAllSessions()) {
			dtos.add(converToDto(session));
		}

		return dtos;
	}

	private SessionDto converToDto(Session session) {
		if(session== null) {
			throw new IllegalArgumentException("There is no such Session.");
		}

		Set<ReviewDto> reviews = new HashSet<ReviewDto>();
		for(Review review : session.getReview()) {
			reviews.add(converToDto(review));
		}

		SessionDto dto = new SessionDto(session.getDate(), session.getStarTime(), session.getEndTime(), session.getIsApproved(), reviews);
		return dto;
	}

	//====================================================================================
	//REVIEW METHOD

	@PostMapping(value = {"/reviews/{tutorEmail}", "/reviews/{tutorEmail}/"})
	public ReviewDto submitTutorReview(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam String textualReview,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {

		Review review = service.submitTutorReview(textualReview, tutorEmail, date, Time.valueOf(startTime), Time.valueOf(endTime));

		return converToDto(review);
	}

	@GetMapping(value = {"/reviews/{tutorEmail}", "/reviews/{tutorEmail}/"})
	public List<ReviewDto> getTutorReviews(@PathVariable("tutorEmail") String tutorEmail) {
		List<Review> tutorReviews = service.getAllTutorReviews(tutorEmail);

		List<ReviewDto> dtos = new ArrayList<ReviewDto>();
		for(Review review : tutorReviews) {
			dtos.add(converToDto(review));
		}

		return dtos;
	}

	private ReviewDto converToDto(Review review) {
		if (review == null) {
			throw new IllegalArgumentException("There is no such course.");
		}

		ReviewDto dto = new ReviewDto(review.getTextualReview(), review.getAuthorEmail(), review.getRating(), review.getReviewID());
		return dto;
	}

	//====================================================================================
}