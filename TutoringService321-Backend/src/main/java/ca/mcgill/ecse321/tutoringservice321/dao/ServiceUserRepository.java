package ca.mcgill.ecse321.tutoringservice321.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice321.model.ServiceUser;

public interface ServiceUserRepository extends CrudRepository<ServiceUser, Integer>{

	ServiceUser findServiceUserById(Integer ID);
	
}
