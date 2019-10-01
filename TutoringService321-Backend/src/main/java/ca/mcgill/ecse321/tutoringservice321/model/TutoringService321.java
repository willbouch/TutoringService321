package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class TutoringService321{
private Availability availability1;
   
   @ManyToOne(optional=false)
   public Availability getAvailability1() {
      return this.availability1;
   }
   
   public void setAvailability1(Availability availability1) {
      this.availability1 = availability1;
   }
   
   private Set<School> school;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<School> getSchool() {
      return this.school;
   }
   
   public void setSchool(Set<School> schools) {
      this.school = schools;
   }
   
   private Set<Subject> subject1;
   
   @OneToMany(mappedBy="tutoringService3211" , cascade={CascadeType.ALL})
   public Set<Subject> getSubject1() {
      return this.subject1;
   }
   
   public void setSubject1(Set<Subject> subject1s) {
      this.subject1 = subject1s;
   }
   
   private Set<Availability> availability;
   
   @OneToMany(mappedBy="tutoringService3211" , cascade={CascadeType.ALL})
   public Set<Availability> getAvailability() {
      return this.availability;
   }
   
   public void setAvailability(Set<Availability> availabilitys) {
      this.availability = availabilitys;
   }
   
   private Set<SpecificAvailability> specificAvailability1;
   
   @OneToMany(mappedBy="tutoringService3211" , cascade={CascadeType.ALL})
   public Set<SpecificAvailability> getSpecificAvailability1() {
      return this.specificAvailability1;
   }
   
   public void setSpecificAvailability1(Set<SpecificAvailability> specificAvailability1s) {
      this.specificAvailability1 = specificAvailability1s;
   }
   
   private Set<User> user1;
   
   @OneToMany(mappedBy="tutoringService3211" , cascade={CascadeType.ALL})
   public Set<User> getUser1() {
      return this.user1;
   }
   
   public void setUser1(Set<User> user1s) {
      this.user1 = user1s;
   }
   
   private Set<Review> review;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Review> getReview() {
      return this.review;
   }
   
   public void setReview(Set<Review> reviews) {
      this.review = reviews;
   }
   
   private Set<Room> room;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Room> getRoom() {
      return this.room;
   }
   
   public void setRoom(Set<Room> rooms) {
      this.room = rooms;
   }
   
   private Set<Session> session;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   @Entity
   public class Subject{
   private Set<Course> course;
   
   @ManyToMany(mappedBy="subject" )
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private Set<Tutor> tutor;
   
   @ManyToMany(mappedBy="subject" )
   public Set<Tutor> getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Set<Tutor> tutors) {
      this.tutor = tutors;
   }
   
   private String subjectName;

public void setSubjectName(String value) {
    this.subjectName = value;
}
public String getSubjectName() {
    return this.subjectName;
}
   private TutoringService321 tutoringService3211;
   
   @ManyToOne(optional=false)
   public TutoringService321 getTutoringService3211() {
      return this.tutoringService3211;
   }
   
   public void setTutoringService3211(TutoringService321 tutoringService3211) {
      this.tutoringService3211 = tutoringService3211;
   }
   
      }
   }
