package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Session{
	private int sessionID;

	public void setSessionID(int value) {
		this.sessionID = value;
	}
	@Id
	public int getSessionID() {
		return this.sessionID;
	}
	private Date date;

	public void setDate(Date value) {
		this.date = value;
	}
	public Date getDate() {
		return this.date;
	}
	private Time starTime;

	public void setStarTime(Time value) {
		this.starTime = value;
	}
	public Time getStarTime() {
		return this.starTime;
	}
	private Time endTime;

	public void setEndTime(Time value) {
		this.endTime = value;
	}
	public Time getEndTime() {
		return this.endTime;
	}
	private Set<Student> student;

	@ManyToMany
	public Set<Student> getStudent() {
		return this.student;
	}

	public void setStudent(Set<Student> students) {
		this.student = students;
	}

	private Tutor tutor;

	@ManyToOne(optional=false)
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
	
	private boolean isApproved;

	public boolean getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	private Set<Review> review;

	@OneToMany(mappedBy = "session", cascade = { CascadeType.ALL })
	public Set<Review> getReview() {
		return this.review;
	}

	public void setReview(Set<Review> reviews) {
		this.review = reviews;
	}
}