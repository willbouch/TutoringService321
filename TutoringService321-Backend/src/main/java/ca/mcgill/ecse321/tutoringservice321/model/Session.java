package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Session {
	private Set<Tutor> tutor;

	@ManyToMany
	public Set<Tutor> getTutor() {
		return this.tutor;
	}

	public void setTutor(Set<Tutor> tutors) {
		this.tutor = tutors;
	}

	private Set<Student> student;

	@ManyToMany
	public Set<Student> getStudent() {
		return this.student;
	}

	public void setStudent(Set<Student> students) {
		this.student = students;
	}

	private Set<Availability> specificAvailability;

	@ManyToMany(mappedBy = "session")
	public Set<Availability> getSpecificAvailability() {
		return this.specificAvailability;
	}

	public void setSpecificAvailability(Set<Availability> specificAvailabilitys) {
		this.specificAvailability = specificAvailabilitys;
	}

	private TutoringService321 tutoringService321;

	@ManyToOne(optional = false)
	public TutoringService321 getTutoringService321() {
		return this.tutoringService321;
	}

	public void setTutoringService321(TutoringService321 tutoringService321) {
		this.tutoringService321 = tutoringService321;
	}

	private int sessionID;

	public void setSessionID(int value) {
		this.sessionID = value;
	}

	@Id
	public int getSessionID() {
		return this.sessionID;
	}

	/**
	 * <pre>
	 *           1..1     1..1
	 * Session ------------------------> Date
	 *           &lt;       date
	 * </pre>
	 */
	private Date date;

	public void setDate(Date value) {
		this.date = value;
	}

	public Date getDate() {
		return this.date;
	}

	/**
	 * <pre>
	 *           1..1     1..1
	 * Session ------------------------> Time
	 *           &lt;       startTime
	 * </pre>
	 */
	private Time startTime;

	public void setStartTime(Time value) {
		this.startTime = value;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	/**
	 * <pre>
	 *           1..1     1..1
	 * Session ------------------------> Time
	 *           &lt;       endTime
	 * </pre>
	 */
	private Time endTime;

	public void setEndTime(Time value) {
		this.endTime = value;
	}

	public Time getEndTime() {
		return this.endTime;
	}

}
