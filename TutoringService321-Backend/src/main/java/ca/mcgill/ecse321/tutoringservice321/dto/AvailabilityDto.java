package ca.mcgill.ecse321.tutoringservice321.dto;

import java.sql.Date;
import java.sql.Time;

public class AvailabilityDto {
	private Date date;
	private Time startTime;
	private Time endTime;
	private TutorDto tutor;
	
	public AvailabilityDto() {
		
	}
	
	public AvailabilityDto(Date date, Time startTime, Time endTime, TutorDto tutor) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.tutor = tutor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public TutorDto getTutor() {
		return tutor;
	}

	public void setTutor(TutorDto tutor) {
		this.tutor = tutor;
	}
	
	
}
