package ca.mcgill.ecse321.tutoringservice321.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
import ca.mcgill.ecse321.tutoringservice321.dto.TutorDto;
import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Session;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;
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
