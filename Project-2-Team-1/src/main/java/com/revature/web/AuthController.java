package com.revature.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.models.User;
import com.revature.service.UserService;
import com.revature.util.JwtTokenManager;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/login")
public class AuthController {
	
	private UserService userv;
	private JwtTokenManager tokenManager;
	
	@Autowired
	public AuthController(UserService userv, JwtTokenManager tokenManager) {
		super();
		this.userv = userv;
		this.tokenManager = tokenManager;
	}
	
	/**
	 * Log in to the application and receive a JWT
	 * @param creds Request's body should be a JSON object with fields "username" and "password"
	 * @param response
	 * @return
	 */
	@PostMapping
	public User login(@RequestBody Credentials creds, HttpServletResponse response) {
		try {
			User user = userv.authenticate(creds);
			String token = tokenManager.issueToken(user);
			
			response.addHeader("adventure-token", token);
			response.addHeader("Access-Control-Expose-Headers", "adventure-token");
			response.setStatus(200);
			
			return user;
		} catch (AuthenticationException e) {
			response.setStatus(401);
			return null;
		}
		
	}
	
	

}