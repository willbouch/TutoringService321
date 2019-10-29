package ca.mcgill.ecse321.tutoringservice321.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

public class SessionDto {

	private Date date;
	private Time startTime;
	private Time endTime;
	private TutorDto tutor;
	private Set<ReviewDto> reviews;
	private boolean isApproved;

	public SessionDto() {

	}

	public SessionDto(Date date, Time startTime, Time endTime, TutorDto tutor, Set<ReviewDto> reviews) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isApproved=false;
		this.tutor = tutor;
		this.reviews = reviews;
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
	
	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public TutorDto getTutor() {
		return tutor;
	}

	public Set<ReviewDto> getReviews() {
		return reviews;
	}

	public void setReviews(Set<ReviewDto> reviews) {
		this.reviews = reviews;
	}
}