package com.revature.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.ReviewRepository;
import com.revature.data.UserRepository;
import com.revature.exceptions.ReviewNotFoundException;
import com.revature.models.Review;

@Service
public class ReviewService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ReviewRepository rRepo;
	
	
	public ReviewService(ReviewRepository rRepo, UserRepository uRepo) {
		super();
		this.rRepo = rRepo;
		
	}
	
	@Transactional(readOnly=true)
	public List<Review> findAll() {		
		return rRepo.findAll().stream().collect(Collectors.toList());
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Review add(Review r) {
		return rRepo.save(r);
	}
	
	@Transactional(propagation=Propagation.REQUIRED) 
	public void remove(int id) {
		
		rRepo.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Review getById(int id) {
		
		if (id <= 0) {
			log.warn("Id cannot be <= 0. Id passed was: {}", id);
			return null;
		} else {
			return rRepo.findById(id)
					.orElseThrow(()-> new ReviewNotFoundException("No review found with id " + id));
		}
		
	}
	
	@Transactional(readOnly = true)
	public List<Review> findByParkCode(String parkCode) {
		if(parkCode.length() != 4) {
			log.warn("Park Code must be a four-letter code");
			return null;
		} else {
			return rRepo.findByParkCodeOrderByDateReviewed(parkCode);
		}
	}
	
	
	

}
