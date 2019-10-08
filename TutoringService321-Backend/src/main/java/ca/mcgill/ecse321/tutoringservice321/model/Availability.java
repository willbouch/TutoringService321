package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Availability{
private TutoringService321 tutoringService321;
   
   @ManyToOne(optional=false)
   public TutoringService321 getTutoringService321() {
      return this.tutoringService321;
   }
   
   public void setTutoringService321(TutoringService321 tutoringService321) {
      this.tutoringService321 = tutoringService321;
   }
   
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
   private Date date;

private void setDate(Date value) {
    this.date = value;
}
private Date getDate() {
    return this.date;
}
private Time startTime;

private void setStartTime(Time value) {
    this.startTime = value;
}
private Time getStartTime() {
    return this.startTime;
}
private Time endTime;

private void setEndTime(Time value) {
    this.endTime = value;
}
private Time getEndTime() {
    return this.endTime;
}
private int availabilityID;

private void setAvailabilityID(int value) {
    this.availabilityID = value;
}
@Id
private int getAvailabilityID() {
    return this.availabilityID;
}
   
   }
