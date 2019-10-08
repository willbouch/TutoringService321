package ca.mcgill.ecse321.tutoringservice321.model;

import Availability;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Tutor extends ServiceUser{
private Set<Availability> availability;
   
   @OneToMany(mappedBy="tutor" , cascade={CascadeType.ALL})
   public Set<Availability> getAvailability() {
      return this.availability;
   }
   
   public void setAvailability(Set<Availability> availabilitys) {
      this.availability = availabilitys;
   }
   
   private Set<Subject> subject;
   
   @ManyToMany(mappedBy="tutor" )
   public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
   }
   
   private int hourlyRate;

private void setHourlyRate(int value) {
    this.hourlyRate = value;
}
private int getHourlyRate() {
    return this.hourlyRate;
}
private float rating;

private void setRating(float value) {
    this.rating = value;
}
private float getRating() {
    return this.rating;
}
   private Set<Session> session;
   
   @OneToMany(mappedBy="tutor" )
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   public void updateAvailability(Availability availability) {
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
