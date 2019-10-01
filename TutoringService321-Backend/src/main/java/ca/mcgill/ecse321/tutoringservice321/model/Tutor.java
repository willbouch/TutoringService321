package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import ca.mcgill.ecse321.tutoringservice321.model.TutoringService321.Subject;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Tutor extends User{
private Set<Availability> availability;
   
   @ManyToMany
   public Set<Availability> getAvailability() {
      return this.availability;
   }
   
   public void setAvailability(Set<Availability> availabilitys) {
      this.availability = availabilitys;
   }
   
   private Set<Subject> subject;
   
   @ManyToMany
   public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
   }
   
   private int pricePerHour;

public void setPricePerHour(int value) {
    this.pricePerHour = value;
}
public int getPricePerHour() {
    return this.pricePerHour;
}
   private Set<Session> session;
   
   @ManyToMany(mappedBy="tutor" )
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   }
