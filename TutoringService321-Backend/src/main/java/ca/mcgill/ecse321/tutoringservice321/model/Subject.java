package ca.mcgill.ecse321.tutoringservice321.model;

import java.util.Set;
import java.util.HashSet;

public class Subject {
   /**
    * <pre>
    *           1..*     1..1
    * Subject ------------------------> TutoringService321
    *           &lt;       tutoringService321
    * </pre>
    */
   private TutoringService321 tutoringService321;
   
   public void setTutoringService321(TutoringService321 value) {
      this.tutoringService321 = value;
   }
   
   public TutoringService321 getTutoringService321() {
      return this.tutoringService321;
   }
   
   private String subjectName;
   
   public void setSubjectName(String value) {
      this.subjectName = value;
   }
   
   public String getSubjectName() {
      return this.subjectName;
   }
   
   /**
    * <pre>
    *           0..*     1..*
    * Subject ------------------------- Course
    *           subject        &lt;       course
    * </pre>
    */
   private Set<Course> course;
   
   public Set<Course> getCourse() {
      if (this.course == null) {
         this.course = new HashSet<Course>();
      }
      return this.course;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Subject ------------------------- Tutor
    *           subject        &gt;       tutor
    * </pre>
    */
   private Set<Tutor> tutor;
   
   public Set<Tutor> getTutor() {
      if (this.tutor == null) {
         this.tutor = new HashSet<Tutor>();
      }
      return this.tutor;
   }
   
   }
