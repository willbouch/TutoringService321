package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
@Entity
public class TutoringService321 {

	private int systemID;

	public void setSystemID(int value) {
		this.systemID = value;
	}

	@Id
	private int getSystemID() {
		return this.systemID;
	}

	private Set<ServiceUser> serviceUser;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<ServiceUser> getServiceUser() {
		return this.serviceUser;
	}

	public void setServiceUser(Set<ServiceUser> serviceUsers) {
		this.serviceUser = serviceUsers;
	}

	private Set<Session> session;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Session> getSession() {
		return this.session;
	}

	public void setSession(Set<Session> sessions) {
		this.session = sessions;
	}

	private Set<Course> course;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Course> getCourse() {
		return this.course;
	}

	public void setCourse(Set<Course> courses) {
		this.course = courses;
	}

	private Set<Subject> subject;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Subject> getSubject() {
		return this.subject;
	}

	public void setSubject(Set<Subject> subjects) {
		this.subject = subjects;
	}

}
