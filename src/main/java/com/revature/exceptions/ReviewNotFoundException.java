package com.revature.exceptions;

public class ReviewNotFoundException extends RuntimeException{

	public ReviewNotFoundException() {
		super("The review was not found");
	}

	public ReviewNotFoundException(String message) {
		super(message);
	}
	

}
