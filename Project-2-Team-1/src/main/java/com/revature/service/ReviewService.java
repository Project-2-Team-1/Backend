package com.revature.service;

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
import com.revature.models.User;

@Service
public class ReviewService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ReviewRepository rRepo;
	private UserRepository uRepo;
	
	
	public ReviewService(ReviewRepository rRepo, UserRepository uRepo) {
		super();
		this.rRepo = rRepo;
		this.uRepo = uRepo;
		
	}
	
	@Transactional(readOnly=true)
	public Set<Review> findAll() {		
		return rRepo.findAll().stream().collect(Collectors.toSet());
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
	
	
	

}
