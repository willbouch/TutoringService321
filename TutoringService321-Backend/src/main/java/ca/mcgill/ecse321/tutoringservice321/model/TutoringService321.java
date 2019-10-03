package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Id;

@EnableAutoConfiguration
@Entity
public class TutoringService321{
   
	private Set<Subject> subject1;
   
   public void setSubject1(Set<Subject> value) {
      this.subject1 = value;
   }
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Subject> getSubject1() {
      return this.subject1;
   }
   
   private Set<Availability> specificAvailability1;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Availability> getSpecificAvailability1() {
      return this.specificAvailability1;
   }
   
   public void setSpecificAvailability1(Set<Availability> specificAvailability1s) {
      this.specificAvailability1 = specificAvailability1s;
   }
   
   private Set<ServiceUser> user1;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<ServiceUser> getUser1() {
      return this.user1;
   }
   
   public void setUser1(Set<ServiceUser> user1s) {
      this.user1 = user1s;
   }
   
   private Set<Session> session;
   
   @OneToMany(mappedBy="tutoringService321" , cascade={CascadeType.ALL})
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   private int systemId;

public void setSystemId(int value) {
    this.systemId = value;
}
@Id
public int getSystemId() {
    return this.systemId;
}
}
