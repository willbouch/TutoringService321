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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ca.mcgill.ecse321.tutoringservice321.dto.AvailabilityDto;
import ca.mcgill.ecse321.tutoringservice321.dto.SessionDto;
import ca.mcgill.ecse321.tutoringservice321.dto.SubjectDto;
import ca.mcgill.ecse321.tutoringservice321.dto.TutorDto;
import ca.mcgill.ecse321.tutoringservice321.dto.CourseDto;
import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Session;
import ca.mcgill.ecse321.tutoringservice321.model.Subject;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
import ca.mcgill.ecse321.tutoringservice321.model.Course;

import ca.mcgill.ecse321.tutoringservice321.service.TutoringService321Service;

@CrossOrigin(origins = "*")
@RestController
public class TutoringService321RestController {

	@Autowired
	private TutoringService321Service service;

	//====================================================================================
	//AVAILABILITY METHODS

	@PostMapping(value = {"/availabilities/{tutorEmail}", "/availabilities/{tutorEmail}/"})
	public AvailabilityDto addAvailability(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {

		Availability availability = service.addAvailability(tutorEmail, date, Time.valueOf(startTime), Time.valueOf(endTime));

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

		TutorDto tutorDto = converToDto(availability.getTutor());
		AvailabilityDto dto = new AvailabilityDto(availability.getDate(), availability.getStartTime(), availability.getEndTime(), tutorDto);
		return dto;
	}

	//====================================================================================
	//TUTOR METHODS

	@PostMapping(value = {"/{tutorEmail}", "/{tutorEmail}/"})
	public TutorDto registerTutor(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam String name,
			@RequestParam String password, 
			@RequestParam String phoneNumber,
			@RequestParam int hourlyRate){

		Tutor tutor = service.createTutor(tutorEmail, name, password, phoneNumber, hourlyRate);

		return converToDto(tutor);
	}

	@GetMapping(value = {"/{tutorEmail}", "/{tutorEmail}/"})
	public TutorDto getTutor(@PathVariable("tutorEmail") String tutorEmail) {
		Tutor tutor = service.getTutor(tutorEmail);

		return converToDto(tutor);
	}
	
	@PutMapping(value = {"/{tutorEmail}", "/{tutorEmail}/"})
	public TutorDto updateProfile(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam String name, 
			@RequestParam String phoneNumber,
			@RequestParam int hourlyRate){

		Tutor tutor = service.updateTutor(tutorEmail, name, phoneNumber, hourlyRate);

		return converToDto(tutor);
	}

	@PutMapping(value = {"/{tutorEmail}", "/{tutorEmail}/"})
	public TutorDto changePassword(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam String oldPassword,
			@RequestParam String newPassword){

		Tutor tutor = service.changePassword(tutorEmail,oldPassword, newPassword);

		return converToDto(tutor);
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

		TutorDto dto = new TutorDto(availabilitiesDto, subjectsDto, sessionsDto, tutor.getHourlyRate(), tutor.getRating());
		return dto;
	}

	//====================================================================================
	//COURSE METHODS

	@GetMapping(value = {"/courses/{courseCode+school}", "/courses/{courseCode+school}/"})
	public CourseDto getCourse(@PathVariable("courseCode"+"school") String courseCode, String school) {
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

	@PostMapping(value = {"/courses/{courseCode+school+subjectName}", "/courses/{courseCode+school+subjectName}/"})
	public CourseDto addCourseToSubject(@PathVariable("courseCode+school+subjectName") String courseCode, String school, String subjectName) {
		Course course = service.addCourseToSubject(school, courseCode, subjectName);

		return converToDto(course);
	}
	
	@DeleteMapping(value = {"/courses/{courseCode+school+subjectName}", "/courses/{courseCode+school+subjectName}/"})
	public List<CourseDto> removeCourseFromSubject(@PathVariable("courseCode+school+subjectName") String courseCode, String school, String subjectName) {
		service.removeCourseFromSubject(school, courseCode, subjectName);

		List<CourseDto> dtos = new ArrayList<CourseDto>();
		for(Course course : service.getAllCourses()) {
			dtos.add(converToDto(course));
		}

		return dtos;
	}

	@GetMapping(value = {"/courses/{tutorEmail}", "/courses/{tutorEmail}/"})
	public List<CourseDto> getTutorCourses(@PathVariable("tutorEmail") String tutorEmail) {
		List<Course> tutorCourses = service.getAllTutorCourses(tutorEmail);

		List<CourseDto> dtos = new ArrayList<CourseDto>();
		for(Course course : tutorCourses) {
			dtos.add(converToDto(course));
		}

		return dtos;
	}
	
	private CourseDto converToDto(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("There is no such course.");
		}

		Set<SubjectDto> subjects = new HashSet<SubjectDto>();
		for(Subject subject : course.getSubject()) {
			subjects.add(converToDto(subject));
		}

		CourseDto dto = new CourseDto(course.getDescription(), course.getSchool(), course.getCourseCode(), subjects);
		return dto;
	}

	//====================================================================================
	//SUBJECT METHODS

	private SubjectDto converToDto(Subject subject) {
		if (subject == null) {
			throw new IllegalArgumentException("There is no such subject.");
		}

		Set<TutorDto> tutors = new HashSet<TutorDto>();
		for(Tutor tutor : subject.getTutor()) {
			tutors.add(converToDto(tutor));
		}

		Set<CourseDto> courses = new HashSet<CourseDto>();
		for(Course course : subject.getCourse()) {
			courses.add(converToDto(course));
		}

		SubjectDto dto = new SubjectDto(subject.getSubjectName(), tutors, courses);
		return dto;
	}
	
	//====================================================================================
	//SESSION METHODS

	@PostMapping(value = {"/sessions/{tutorEmail}", "/session/{tutorEmail}/"})
	public SessionDto createSession(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {

		Session session= service.createSession(tutorEmail, date, Time.valueOf(startTime), Time.valueOf(endTime));

		return converToDto(session);
	}

	@GetMapping(value = {"/availabilities/{tutorEmail}", "/availabilities/{tutorEmail}/"})
	public List<SessionDto> getAllSessions(@PathVariable("tutorEmail") String tutorEmail) {
		List<SessionDto> dtos = new ArrayList<SessionDto>();
		for(Session session : service.getAllSessions(tutorEmail)) {
			dtos.add(converToDto(session));
		}

		return dtos;
	}

	@PutMapping(value = {"/availabilities/{tutorEmail}", "/availabilities/{tutorEmail}/"})
	public SessionDto approveSession(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date requestedDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime qStartTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime qEndTime,
			@RequestParam Date confirmedDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime cStartTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime cEndTime) {

		Session session= service.approveSession(tutorEmail, requestedDate, Time.valueOf(qStartTime), Time.valueOf(qEndTime), confirmedDate, Time.valueOf(cStartTime), Time.valueOf(cEndTime));
		return converToDto(session);
	}

	@DeleteMapping(value = {"/session/{tutorEmail}", "/session/{tutorEmail}/"})
	public List<SessionDto> cancelSession(@PathVariable("tutorEmail") String tutorEmail,
			@RequestParam Date date,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime endTime) {
		service.cancelSession(tutorEmail, date, Time.valueOf(startTime), Time.valueOf(endTime));

		List<SessionDto> dtos = new ArrayList<SessionDto>();
		for(Session session : service.getAllSessions(tutorEmail)) {
			dtos.add(converToDto(session));
		}

		return dtos;
	}

	private SessionDto converToDto(Session session) {
		if(session== null) {
			throw new IllegalArgumentException("There is no such Session.");
		}

		TutorDto tutorDto = converToDto(session.getTutor());
		SessionDto dto = new SessionDto(session.getDate(), session.getStarTime(), session.getEndTime(), tutorDto, null);
		return dto;
	}

	//====================================================================================
}
