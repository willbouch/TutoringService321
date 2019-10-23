package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Course{
   private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
private String school;

public void setSchool(String value) {
    this.school = value;
}
public String getSchool() {
    return this.school;
}
private String courseCode;

public void setCourseCode(String value) {
    this.courseCode = value;
}
public String getCourseCode() {
    return this.courseCode;
}
private int courseID;

public void setCourseID(int value) {
    this.courseID = value;
}
@Id
public int getCourseID() {
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

   }
