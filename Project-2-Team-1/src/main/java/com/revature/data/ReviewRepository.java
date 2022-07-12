package com.revature.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	List<Review> findByOrderByRating();
	
	List<Review> findByOrderByDateReviewed();
	
	List<Review> findReviewByUserId(int userId);
	
	List<Review> findByParkCode(String parkCode);

}
