package ca.mcgill.ecse321.tutoringservice321.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice321.model.Availability;

public interface AvailabilityRepository extends CrudRepository<Availability, Integer>{
	
	Availability findAvailabilityByID(Integer ID);
	
}
