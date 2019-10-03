package ca.mcgill.ecse321.tutoringservice321.model;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import java.sql.Date;
import java.sql.Time;

@Entity
public class Tutor extends ServiceUser {
private Set<Subject> subject;
   
   @ManyToMany(cascade = { CascadeType.ALL })
   public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
   }
   
	private int hourlyRate;

	public void setHourlyRate(int value) {
		this.hourlyRate = value;
	}

	public int getHourlyRate() {
		return this.hourlyRate;
	}

	private Set<Session> session;

	@ManyToMany(mappedBy = "tutor")
	public Set<Session> getSession() {
		return this.session;
	}

	public void setSession(Set<Session> sessions) {
		this.session = sessions;
	}

	private Set<Availability> availability;

	@ManyToMany(mappedBy = "tutor")
	public Set<Availability> getAvailability() {
		return this.availability;
	}

	public void setAvailability(Set<Availability> availabilitys) {
		this.availability = availabilitys;
	}

	private float rating;

	public void setRating(float value) {
		this.rating = value;
	}

	public float getRating() {
		return this.rating;
	}

	public void updateAvailability(Availability availabilty) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void rescheduleSession(int sessionID, Date newDate, Time newTime) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

	public void cancelSession(int sessionID) {
		// TODO implement this operation
		throw new UnsupportedOperationException("not implemented");
	}

}
