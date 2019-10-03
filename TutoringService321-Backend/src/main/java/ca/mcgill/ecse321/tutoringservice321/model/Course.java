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
@Id
public String getCourseCode() {
    return this.courseCode;
}
private Set<Subject> subject;

@ManyToMany(mappedBy="course" )
public Set<Subject> getSubject() {
   return this.subject;
}

public void setSubject(Set<Subject> subjects) {
   this.subject = subjects;
}

private int courseId;

public void setCourseId(int value) {
    this.courseId = value;
}
@Id
public int getCourseId() {
    return this.courseId;
}
}
