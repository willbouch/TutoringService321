package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Tutor extends ServiceUser {
	private Set<Availability> availability;

	@OneToMany(mappedBy = "tutor", cascade = { CascadeType.ALL })
	public Set<Availability> getAvailability() {
		return this.availability;
	}

	public void setAvailability(Set<Availability> availabilitys) {
		this.availability = availabilitys;
	}

	private Set<Subject> subject;

	@ManyToMany(mappedBy = "tutor")
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

	private float rating;

	public void setRating(float value) {
		this.rating = value;
	}

	public float getRating() {
		return this.rating;
	}

	private Set<Session> session;

	@OneToMany(mappedBy = "tutor")
	public Set<Session> getSession() {
		return this.session;
	}

	public void setSession(Set<Session> sessions) {
		this.session = sessions;
	}

}
