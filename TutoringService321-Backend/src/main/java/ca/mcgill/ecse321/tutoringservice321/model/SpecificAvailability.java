package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class SpecificAvailability{
private Set<Availability> availability1;
   
   @ManyToMany(mappedBy="specificAvailability" )
   public Set<Availability> getAvailability1() {
      return this.availability1;
   }
   
   public void setAvailability1(Set<Availability> availability1s) {
      this.availability1 = availability1s;
   }
   
   private Availability availability;
   
   @ManyToOne(optional=false)
   public Availability getAvailability() {
      return this.availability;
   }
   
   public void setAvailability(Availability availability) {
      this.availability = availability;
   }
   
   private boolean isAvailable;

public void setIsAvailable(boolean value) {
    this.isAvailable = value;
}
public boolean isIsAvailable() {
    return this.isAvailable;
}
private String date;

public void setDate(String value) {
    this.date = value;
}
public String getDate() {
    return this.date;
}
   private TutoringService321 tutoringService3211;
   
   @ManyToOne(optional=false)
   public TutoringService321 getTutoringService3211() {
      return this.tutoringService3211;
   }
   
   public void setTutoringService3211(TutoringService321 tutoringService3211) {
      this.tutoringService3211 = tutoringService3211;
   }
   
   private Set<Session> session;
   
   @ManyToMany
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   }
