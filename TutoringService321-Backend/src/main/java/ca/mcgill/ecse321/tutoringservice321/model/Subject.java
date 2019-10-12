package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Subject{
private int subjectID;

public void setSubjectID(int value) {
this.subjectID = value;
}
@Id
private int getSubjectID() {
return this.subjectID;
}
private Set<Tutor> tutor;
   
   @ManyToMany
   public Set<Tutor> getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Set<Tutor> tutors) {
      this.tutor = tutors;
   }
   
   private String subjectName;

   public void setSubjectName(String value) {
    this.subjectName = value;
}
   public String getSubjectName() {
    return this.subjectName;
}
   private Set<Course> course;
   
   @ManyToMany
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
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
