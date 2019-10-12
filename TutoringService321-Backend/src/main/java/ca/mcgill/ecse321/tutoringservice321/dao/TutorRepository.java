package ca.mcgill.ecse321.tutoringservice321.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice321.model.Availability;
import ca.mcgill.ecse321.tutoringservice321.model.Tutor;

public interface TutorRepository extends CrudRepository<Tutor, Integer>{
	
	Set<Tutor> findTutorByName(String name);
	Tutor findTutorByEmail(String email);
	
}
