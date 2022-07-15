package com.revature.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.ReviewRepository;
import com.revature.data.UserRepository;
import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Review;
import com.revature.models.User;

@Service
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private UserRepository uRepo;
	private ReviewRepository rRepo;
	
	@Autowired
	public UserService(UserRepository uRepo, ReviewRepository rRepo) {
		super();
		this.uRepo = uRepo;
		this.rRepo = rRepo;
	}
	
	public User authenticate(Credentials creds) throws AuthenticationException {
		
		User user = uRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword())
				.orElseThrow(AuthenticationException::new);
		if (user != null) {
			log.info("User successfully authenticated");
		} else {
			log.error("User not authenticated");
		}
		return user;
	}
	
	

	@Transactional(readOnly=true)
	public List<User> findAll() {		
		List<User> list = uRepo.findAll().stream().collect(Collectors.toList());
		if (list != null) {
			log.info("User List successfully retrieved");
		} else {
			log.error("User List was not retrieved");
		}
		return list;
	}
	
	@Transactional(readOnly=true)
	public List<Review> findReviewByUserId(int id){
		List<Review> reviews = rRepo.findReviewByUserId(id);
		if (reviews != null) {
			log.info("Reviews with User Id: " + id + " were successfully retrieved");
		} else {
			log.error("Reviews were not retrieved");
		}
		return reviews;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User add(User u) {
		System.out.println(u);
		User newU = uRepo.save(u);
		if (newU.getId() > 0) {
			log.info("New User successfully added");
		} else {
			log.error("New user not added");
		}
		return newU;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public User updateUser(User u) {
		return uRepo.save(u);
	}
	
	@Transactional(propagation=Propagation.REQUIRED) 
	public void remove(int id) {
		log.info("User with User Id: " + id + " was deleted");
		uRepo.deleteById(id);		
	}
	
	@Transactional(readOnly=true)
	public User getByUsername(String username) {
			Optional<User> u = uRepo.findByUsername(username);
			if (u.isPresent()) {
			log.info("User with username: " + username + " was successfully retrieved");
			return u.get();
			} else {
				u.orElseThrow(() -> new UserNotFoundException("No user found with username " + username));
				return null;
			}
		
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


















