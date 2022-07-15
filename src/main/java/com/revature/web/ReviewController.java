package com.revature.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.revature.models.Review;
import com.revature.service.ReviewService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewService rserv;
	
	/**
	 * Retrieve a set of Reviews objects in JSON
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<Review>> getAll() {
		return ResponseEntity.ok(rserv.findAll());
	}
	
	@GetMapping("/park/{parkCode}")
	public ResponseEntity<List<Review>> getReviewsByParkCode(@PathVariable("parkCode") String parkCode) {
		return ResponseEntity.ok(rserv.findByParkCode(parkCode));
	}
	
	/**
	 * 
	 * @param r a JSON Review object, for example:
	 * 	{
	 * 		"content": "Optional text hear",
	 * 		"parkCode": "mora",
	 * 		"rating": 5,
	 * 		"user": {
	 * 			"id": 1
	 * 		}
	 * 	}
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Review> postReview(@RequestBody Review r) {
		return ResponseEntity.ok(rserv.add(r));
	}
	
	/**
	 * Deletes a Review record by ID of the Review
	 * @param id
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReview(@PathVariable("id") int id) {
		try{
			rserv.remove(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review with ID " + id + " not found");
		}
		return ResponseEntity.ok("Deleted Review " + id);
	}
}
