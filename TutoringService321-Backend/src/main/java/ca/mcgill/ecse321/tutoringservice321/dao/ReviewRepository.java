package ca.mcgill.ecse321.tutoringservice321.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringservice321.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer>{

	Set<Review> findReviewByReviewID(int ID);
	
}
