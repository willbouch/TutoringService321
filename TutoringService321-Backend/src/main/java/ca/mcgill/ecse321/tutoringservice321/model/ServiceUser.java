package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(
		strategy = InheritanceType.JOINED
)

public class ServiceUser{
private int userID;

private void setUserID(int value) {
this.userID = value;
}
@Id
private int getUserID() {
return this.userID;
}
   private String email;

private void setEmail(String value) {
    this.email = value;
}
private String getEmail() {
    return this.email;
}
private String name;

private void setName(String value) {
    this.name = value;
}
private String getName() {
    return this.name;
}
private String phoneNumber;

private void setPhoneNumber(String value) {
    this.phoneNumber = value;
}
private String getPhoneNumber() {
    return this.phoneNumber;
}
private String password;

private void setPassword(String value) {
    this.password = value;
}
private String getPassword() {
    return this.password;
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
