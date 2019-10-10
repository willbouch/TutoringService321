package ca.mcgill.ecse321.tutoringservice321.dao;


import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice321.model.*;

public interface SubjectRepository extends CrudRepository<Subject, Integer>{
	
	Subject findSubjectBySubjectName(String name);
	Set<Subject> findSubjectByTutor(Tutor tutor);
	
}
