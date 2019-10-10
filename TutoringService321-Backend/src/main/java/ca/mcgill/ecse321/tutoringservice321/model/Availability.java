package ca.mcgill.ecse321.tutoringservice321.model;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Availability{
   
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
   private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private Time startTime;

public void setStartTime(Time value) {
    this.startTime = value;
}
public Time getStartTime() {
    return this.startTime;
}
private Time endTime;

public void setEndTime(Time value) {
    this.endTime = value;
}
public Time getEndTime() {
    return this.endTime;
}
private int availabilityID;

public void setAvailabilityID(int value) {
    this.availabilityID = value;
}
@Id
private int getAvailabilityID() {
    return this.availabilityID;
}
   
   }
