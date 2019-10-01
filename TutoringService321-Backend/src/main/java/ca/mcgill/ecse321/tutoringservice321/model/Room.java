package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Room{
   private Set<Session> session;
   
   @ManyToMany
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
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
