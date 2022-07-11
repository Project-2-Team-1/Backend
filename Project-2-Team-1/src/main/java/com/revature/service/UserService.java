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
import com.revature.data.UserRepository;
import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Park;
import com.revature.models.Review;
import com.revature.models.User;

@Service
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private UserRepository uRepo;
	private ReviewRepository rRepo;
	private ParkRepository pRepo;
	
	@Autowired
	public UserService(UserRepository uRepo, ReviewRepository rRepo, ParkRepository pRepo) {
		super();
		this.uRepo = uRepo;
		this.rRepo = rRepo;
		this.pRepo = pRepo;
	}
	
	public User authenticate(Credentials creds) {
		
		User user = uRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword())
	    .orElseThrow(AuthenticationException::new);
		return user;
	}
	
	

	@Transactional(readOnly=true)
	public Set<User> findAll() {		
		return uRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	@Transactional(readOnly=true)
	public List<Review> findReviewByUserId(int id){
		return rRepo.findByUserId();
	}
	
	@Transactional(readOnly=true)
	public List<Park> findParkByUserId(int id){
		return pRepo.findByUserId();
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User add(User u) {
		return uRepo.save(u);
	}
	
	@Transactional(propagation=Propagation.REQUIRED) 
	public void remove(int id) {
		
		uRepo.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public User getByUsername(String username) {
		return uRepo.findByUsername(username) 
				.orElseThrow(() -> new UserNotFoundException("No user found with username " + username));
	}
	
	@Transactional(readOnly=true)
	public User getById(int id) {
		
		if (id <= 0) {
			log.warn("Id cannot be <= 0. Id passed was: {}", id);
			return null;
		} else {
			return uRepo.findById(id)
					.orElseThrow(()-> new UserNotFoundException("No user found with id " + id));
		}
		
	}
	

}


















