package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Course{
   private String description;

private void setDescription(String value) {
    this.description = value;
}
private String getDescription() {
    return this.description;
}
private String school;

private void setSchool(String value) {
    this.school = value;
}
private String getSchool() {
    return this.school;
}
private String courseCode;

private void setCourseCode(String value) {
    this.courseCode = value;
}
private String getCourseCode() {
    return this.courseCode;
}
private int courseID;

private void setCourseID(int value) {
    this.courseID = value;
}
@Id
private int getCourseID() {
    return this.courseID;
}
   private Set<Subject> subject;
   
   @ManyToMany(mappedBy="course")
public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
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
