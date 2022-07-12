package com.revature.web;

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
	
	@GetMapping
	public ResponseEntity<Set<User>> getAll() {
		return ResponseEntity.ok(userv.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		return ResponseEntity.ok(userv.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<User> register(@RequestBody User u) {
		return ResponseEntity.ok(userv.add(u));
	}
}
