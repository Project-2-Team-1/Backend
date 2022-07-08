package com.revature.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.ParkRepository;
import com.revature.data.ReviewRepository;
import com.revature.exceptions.ParkNotFoundException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Park;
import com.revature.models.Review;
import com.revature.models.User;


@Service
public class ParkService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ParkRepository pRepo;
	private ReviewRepository rRepo;
	
	@Autowired
	public ParkService(ParkRepository pRepo, ReviewRepository rRepo) {
		super();
		this.pRepo = pRepo;
		this.rRepo = rRepo;
	}
	
	@Transactional(readOnly=true)
	public Set<Park> findAll() {		
		return pRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	@Transactional(readOnly=true)
	public List<Review> findReviewByParkId(int id){
		return rRepo.findByUserId();
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public Park add(Park p) {
		return pRepo.save(p);
	}
	
	@Transactional(propagation=Propagation.REQUIRED) 
	public void remove(int id) {
		
		pRepo.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Park getByParkName(String parkName) {
		return pRepo.findByParkName(parkName) 
				.orElseThrow(() -> new ParkNotFoundException("No park found with parkName " + parkName));
	}
	
	@Transactional(readOnly=true)
	public Park getById(int id) {
		
		if (id <= 0) {
			log.warn("Id cannot be <= 0. Id passed was: {}", id);
			return null;
		} else {
			return pRepo.findById(id)
					.orElseThrow(()-> new ParkNotFoundException("No park found with id " + id));
		}
		
	}

}
