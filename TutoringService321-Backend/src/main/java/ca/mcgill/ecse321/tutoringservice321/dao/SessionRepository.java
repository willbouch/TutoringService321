package ca.mcgill.ecse321.tutoringservice321.dao;

import java.sql.Date;
import java.util.Set;
import ca.mcgill.ecse321.tutoringservice321.model.*;

import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Integer>{
	
	Set<Session> findByStudent(Student student);
	Set<Session> findByTutor(Tutor tutor);
	Set<Session> findByTutorAndStudent(Tutor tutor, Student student);
	Set<Session> findByDate(Date date);
	
}
