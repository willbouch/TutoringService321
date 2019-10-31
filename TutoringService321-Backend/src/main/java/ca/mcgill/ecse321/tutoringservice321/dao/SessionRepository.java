package ca.mcgill.ecse321.tutoringservice321.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;
import ca.mcgill.ecse321.tutoringservice321.model.*;

import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Integer>{
	
	Set<Session> findSessionByStudent(Student student);
	Tutor findSessionByTutor(String tutor);
	Set<Session> findSessionByTutor(Tutor tutor);
	Set<Session> findSessionByTutorAndDate(Tutor tutor, Date date);
	Set<Session> findSessionByTutorAndStudent(Tutor tutor, Student student);
	Set<Session> findSessionByDate(Date date);
	Set<Session> findSessionBySessionID(int id);
	
}
