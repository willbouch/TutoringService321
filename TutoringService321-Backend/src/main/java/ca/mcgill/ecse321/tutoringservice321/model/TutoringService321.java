package ca.mcgill.ecse321.tutoringservice321.model;

import Availability;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@EnableAutoConfiguration
@Entity
public class TutoringService321{
private Set<Availability> availability1;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Availability> getAvailability1() {
      return this.availability1;
   }
   
   public void setAvailability1(Set<Availability> availability1s) {
      this.availability1 = availability1s;
   }
   
   private int systemID;

private void setSystemID(int value) {
    this.systemID = value;
}
@Id
private int getSystemID() {
    return this.systemID;
}
   private Set<ServiceUser> serviceUser;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<ServiceUser> getServiceUser() {
      return this.serviceUser;
   }
   
   public void setServiceUser(Set<ServiceUser> serviceUsers) {
      this.serviceUser = serviceUsers;
   }
   
   private Set<Availability> availability;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Availability> getAvailability() {
      return this.availability;
   }
   
   public void setAvailability(Set<Availability> availabilitys) {
      this.availability = availabilitys;
   }
   
   private Set<Session> session;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   private Set<Course> course;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private Set<Subject> subject;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
   }
   
   }
