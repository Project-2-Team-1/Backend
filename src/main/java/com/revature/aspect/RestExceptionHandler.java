package com.revature.aspect;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.errorhandling.ApiError;
import com.revature.errorhandling.ApiValidationError;
import com.revature.exceptions.ParkNotFoundException;
import com.revature.exceptions.ReviewNotFoundException;
import com.revature.exceptions.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String error = "Request failed validation";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, error);
		
		for (FieldError e : ex.getFieldErrors()) {
			
			apiError.addSubError(new ApiValidationError(e.getObjectName(), 
					e.getDefaultMessage(), e.getField(), e.getRejectedValue()));
		}
		
		return buildResponseEntity(apiError); 
 	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String error = "Malformed JSON request";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, error);
		
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(UserNotFoundException.class) 
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
		
		String error = "No User found with that username";
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex, error);
		
		return buildResponseEntity(apiError);	
	}
	
	@ExceptionHandler(ParkNotFoundException.class) 
	public ResponseEntity<Object> handleParkNotFoundException(ParkNotFoundException ex) {
		
		String error = "No Park found with that name";
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex, error);
		
		return buildResponseEntity(apiError);	
	}
	
	@ExceptionHandler(ReviewNotFoundException.class) 
	public ResponseEntity<Object> handleReviewNotFoundException(ReviewNotFoundException ex) {
		
		String error = "No Review found with that review id";
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex, error);
		
		return buildResponseEntity(apiError);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
