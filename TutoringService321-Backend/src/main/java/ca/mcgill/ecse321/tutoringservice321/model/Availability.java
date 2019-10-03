package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import java.sql.Time;
import javax.persistence.Id;

@Entity
public class Availability{
   private String date;
   
   public void setDate(String value) {
      this.date = value;
   }
   
   public String getDate() {
      return this.date;
   }
   
   private TutoringService321 tutoringService321;
   
   @ManyToOne(optional=false)
   public TutoringService321 getTutoringService321() {
      return this.tutoringService321;
   }
   
   public void setTutoringService321(TutoringService321 tutoringService321) {
      this.tutoringService321 = tutoringService321;
   }
   
   private Set<Session> session;
   
   @ManyToMany
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   /**
    * <pre>
    *           1..1     1..1
    * Availability ------------------------> Time
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
    * Availability ------------------------> Time
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
   
   private Set<Tutor> tutor;
   
   @ManyToMany
   public Set<Tutor> getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Set<Tutor> tutors) {
      this.tutor = tutors;
   }
   
   private int availabilityID;

public void setAvailabilityID(int value) {
    this.availabilityID = value;
}
@Id
public int getAvailabilityID() {
    return this.availabilityID;
}
}
