package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class User{
   private String email;

public void setEmail(String value) {
    this.email = value;
}
public String getEmail() {
    return this.email;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
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
