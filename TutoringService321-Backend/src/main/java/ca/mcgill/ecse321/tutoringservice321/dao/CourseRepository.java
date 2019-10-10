package ca.mcgill.ecse321.tutoringservice321.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice321.model.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>{

	Course findCourseByID(Integer ID);
	
}
