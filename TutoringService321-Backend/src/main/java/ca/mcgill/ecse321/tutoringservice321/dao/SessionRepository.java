package ca.mcgill.ecse321.tutoringservice321.dao;

import java.sql.Date;
import java.util.Set;
import ca.mcgill.ecse321.tutoringservice321.model.*;

import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Integer>{
	
	Set<Session> findSessionByStudent(Student student);
	Set<Session> findSessionByTutor(Tutor tutor);
	Set<Session> findSessionByTutorAndStudent(Tutor tutor, Student student);
	Set<Session> findSessionByDate(Date date);
	
}
