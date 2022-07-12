package com.revature.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Review;
import com.revature.service.ReviewService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService rserv;
	
	@GetMapping
	public ResponseEntity<Set<Review>> getAll() {
		return ResponseEntity.ok(rserv.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Review> postReview(@RequestBody Review r) {
		return ResponseEntity.ok(rserv.add(r));
	}
	
	@DeleteMapping("/{id}")
	public void deleteReview(@PathVariable("id") int id) {
		rserv.remove(id);
	}
}
