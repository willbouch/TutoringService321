package ca.mcgill.ecse321.tutoringservice321.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

public class TutorDto {
	private Set<AvailabilityDto> availabilities;
	private Set<SubjectDto> subjects;
	private int hourlyrate;
	private float rating;
	private Set<SessionDto> sessions;
	
	
	public TutorDto(Set<AvailabilityDto> availabilities, Set<SubjectDto> subjects, Set<SessionDto> sessions, int hourlyrate, float rating) {
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
	

}
