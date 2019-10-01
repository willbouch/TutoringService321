package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class School{
   private Set<Course> course;
   
   @OneToMany(mappedBy="school" )
   public Set<Course> getCourse() {
      return this.course;
   }
   
   public void setCourse(Set<Course> courses) {
      this.course = courses;
   }
   
   private EducationLevel level;
   
   @ManyToOne(optional=false)
   public EducationLevel getLevel() {
      return this.level;
   }
   
   public void setLevel(EducationLevel level) {
      this.level = level;
   }
   
   private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
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
