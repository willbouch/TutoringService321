package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import ca.mcgill.ecse321.tutoringservice321.model.TutoringService321.Subject;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Course{
   private Set<Subject> subject;
   
   @ManyToMany
   public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
   }
   
   private String courseID;

public void setCourseID(String value) {
    this.courseID = value;
}
public String getCourseID() {
    return this.courseID;
}
private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
   private School school;
   
   @ManyToOne(optional=false)
   public School getSchool() {
      return this.school;
   }
   
   public void setSchool(School school) {
      this.school = school;
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
