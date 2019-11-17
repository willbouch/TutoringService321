package ca.mcgill.ecse321.tutoringservice321.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

public class TutorDto {
	private String email;
	private String name;
	private String password;
	private String phoneNumber;
	private Set<AvailabilityDto> availabilities;
	private Set<SubjectDto> subjects;
	private int hourlyrate;
	private float rating;
	private Set<SessionDto> sessions;
	
	
	public TutorDto(String email, String name, String password, String phoneNumber, Set<AvailabilityDto> availabilities, Set<SubjectDto> subjects, Set<SessionDto> sessions, int hourlyrate, float rating) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.availabilities = availabilities;
		this.subjects = subjects;
		this.sessions = sessions;
		this.hourlyrate = hourlyrate;
		this.rating = rating;
	}

	public Set<SubjectDto> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<SubjectDto> subjects) {
		this.subjects = subjects;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public Set<SessionDto> getSessions() {
		return sessions;
	}

	public void setSessions(Set<SessionDto> sessions) {
		this.sessions = sessions;
	}

	public int getHourlyrate() {
		return hourlyrate;
	}

	public void setHourlyrate(int hourlyrate) {
		this.hourlyrate = hourlyrate;
	}

	public Set<AvailabilityDto> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(Set<AvailabilityDto> availabilities) {
		this.availabilities = availabilities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}