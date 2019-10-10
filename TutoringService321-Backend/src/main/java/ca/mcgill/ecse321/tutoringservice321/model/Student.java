package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Student{
   private int studentID;

public void setStudentID(int value) {
    this.studentID = value;
}
@Id
private int getStudentID() {
    return this.studentID;
}
   private Set<Session> session;
   
   @ManyToMany(mappedBy="student" )
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   }
