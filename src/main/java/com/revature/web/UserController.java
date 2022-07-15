package com.revature.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.service.UserService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userv;
	
	/**
	 * Retrieve a JSON list of all users
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(userv.findAll());
	}
	
	/**
	 * Retrieve a single user by ID
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		return ResponseEntity.ok(userv.getById(id));
	}
	
	/**
	 * Persist a new user to the application
	 * @param u a JSON User object, for example:
	 *  {
	 *		"email": "email@gmail.com",
	 *		"username": "username",
	 *		"firstName": "User",
	 *		"lastName": "Name",
	 *		"password": "password123"
	 *	}
	 * @return
	 */
	@PostMapping
	public ResponseEntity<User> register(@RequestBody User u) {
		return ResponseEntity.ok(userv.add(u));
	}
}
