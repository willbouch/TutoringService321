package ca.mcgill.ecse321.tutoringservice321.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;

public interface AvailabilityRepository extends CrudRepository<Availability, Integer>{
	
	Set<Availability> findAvailabilityByStartTime(Time stime);
	Set<Availability> findAvailabilityByDate(Date date);
	Set<Availability> findAvailabilityByDateAndTutor(Date date, Tutor tutor);
	Set<Availability> findAvailabilityByTutor(Tutor tutor);
	Set<Availability> findAvailabilityByTutorAndDateAndStartTime(Tutor tutor,Date date, Time stime);
	
}
