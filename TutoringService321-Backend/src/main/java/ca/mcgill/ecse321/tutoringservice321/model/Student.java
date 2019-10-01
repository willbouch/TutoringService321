package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Student extends User{
   private Set<Session> session;
   
   @ManyToMany(mappedBy="student" )
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   }
