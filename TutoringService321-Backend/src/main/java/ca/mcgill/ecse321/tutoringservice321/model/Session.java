package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Session{
   private Set<Student> student;
   
   @ManyToMany
   public Set<Student> getStudent() {
      return this.student;
   }
   
   public void setStudent(Set<Student> students) {
      this.student = students;
   }
   
   private Set<Tutor> tutor;
   
   @ManyToMany
   public Set<Tutor> getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Set<Tutor> tutors) {
      this.tutor = tutors;
   }
   
   private Set<Course> course;
   
   @ManyToMany(mappedBy="session" )
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private Set<SpecificAvailability> specificAvailability;
   
   @ManyToMany(mappedBy="session" )
   public Set<SpecificAvailability> getSpecificAvailability() {
      return this.specificAvailability;
   }
   
   public void setSpecificAvailability(Set<SpecificAvailability> specificAvailabilitys) {
      this.specificAvailability = specificAvailabilitys;
   }
   
   private Set<Review> review;
   
   @ManyToMany(mappedBy="session" )
   public Set<Review> getReview() {
      return this.review;
   }
   
   public void setReview(Set<Review> reviews) {
      this.review = reviews;
   }
   
   private Set<Room> room;
   
   @ManyToMany(mappedBy="session" )
   public Set<Room> getRoom() {
      return this.room;
   }
   
   public void setRoom(Set<Room> rooms) {
      this.room = rooms;
   }
   
   private TutoringService321 tutoringService321;
   
   @ManyToOne(optional=false)
   public TutoringService321 getTutoringService321() {
      return this.tutoringService321;
   }
   
   public void setTutoringService321(TutoringService321 tutoringService321) {
      this.tutoringService321 = tutoringService321;
   }
   
   }
