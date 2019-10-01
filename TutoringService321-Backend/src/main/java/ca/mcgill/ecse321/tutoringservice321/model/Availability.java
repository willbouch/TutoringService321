package ca.mcgill.ecse321.tutoringservice321.model;

import java.util.Set;
import java.util.HashSet;

public class Availability {
   /**
    * <pre>
    *           0..*     0..*
    * Availability ------------------------- SpecificAvailability
    *           availability1        &lt;       specificAvailability
    * </pre>
    */
   private Set<SpecificAvailability> specificAvailability;
   
   public Set<SpecificAvailability> getSpecificAvailability() {
      if (this.specificAvailability == null) {
         this.specificAvailability = new HashSet<SpecificAvailability>();
      }
      return this.specificAvailability;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Availability ------------------------- Tutor
    *           availability        &gt;       tutor
    * </pre>
    */
   private Set<Tutor> tutor;
   
   public Set<Tutor> getTutor() {
      if (this.tutor == null) {
         this.tutor = new HashSet<Tutor>();
      }
      return this.tutor;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Availability ------------------------- TutoringService321
    *           availability1        &gt;       tutoringService321
    * </pre>
    */
   private Set<TutoringService321> tutoringService321;
   
   public Set<TutoringService321> getTutoringService321() {
      if (this.tutoringService321 == null) {
         this.tutoringService321 = new HashSet<TutoringService321>();
      }
      return this.tutoringService321;
   }
   
   }
