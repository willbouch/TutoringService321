package ca.mcgill.ecse321.tutoringservice321.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
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

import ca.mcgill.ecse321.tutoringservice321.dto.*;
import ca.mcgill.ecse321.tutoringservice321.model.*;
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
	
	private TutorDto converToDto(Tutor tutor) {
		//TODO
		if(tutor == null) {
			throw new IllegalArgumentException("There is no such Tutor.");
		}
		
		TutorDto dto = new TutorDto();
		return dto;
	}
	
	//====================================================================================
	// Course Methods
	
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
	
//	@PostMapping(value = {"/courses/{course+tutorEmail}", "/courses/{course+tutorEmail}/"})
//	public CourseDto addCourseToTutor(@PathVariable("course+tutorEmail") Course course, String tutorEmail) {
//		Tutor tutor = service.getTutor(tutorEmail);
//		
//		return converToDto(course, tutor);
//	}
	
	private CourseDto converToDto(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("There is no such course.");
		}
		
		SubjectDto subjectDto = converToDto(course.getSubject());
		CourseDto dto = new CourseDto(course.getDescription(), course.getCourseCode(), course.getSchool(), subjectDto);
		return dto;
	}
	
	
	//====================================================================================
	// Subject Methods

	private SubjectDto converToDto(Set<Subject> subject) {
		//TODO
		if (subject == null) {
			throw new IllegalArgumentException("There is no such subject.");
		}
		
		SubjectDto dto = new SubjectDto();
		return dto;
	}
	
}
