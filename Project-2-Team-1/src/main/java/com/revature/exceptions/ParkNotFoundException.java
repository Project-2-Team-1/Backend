package com.revature.exceptions;

public class ParkNotFoundException extends RuntimeException{

	public ParkNotFoundException() {
		super("Park not found");		
	}

	public ParkNotFoundException(String message) {
		super(message);
	}
	

}
