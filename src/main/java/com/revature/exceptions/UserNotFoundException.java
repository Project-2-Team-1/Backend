package com.revature.exceptions;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException() {
		super("User was not found");
	}

	public UserNotFoundException(String message) {
		super(message);
	}
	
	

}
