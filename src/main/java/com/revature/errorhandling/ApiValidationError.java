package com.revature.errorhandling;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)  
public class ApiValidationError extends ApiSubError {

	private String object; 
	private String field; 
	private Object rejectedValue; 
	private String reason; 
	
	
	public ApiValidationError(String object, String reason) {
		this.object = object;
		this.reason = reason;
	}
	
	public ApiValidationError(String object, String field, String reason) {
		this(object, reason); 
		this.field = field;
	}
	
	public ApiValidationError(String object, String field, String reason, Object rejectedValue) {
		this(object, field, reason); 
		this.rejectedValue = rejectedValue;
	}
	
}