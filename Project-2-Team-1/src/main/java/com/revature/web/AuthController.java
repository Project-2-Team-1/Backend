package com.revature.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.Credentials;
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
	
	@PostMapping
	public User login(Credentials creds, HttpServletResponse response) {
		
		User user = userv.authenticate(creds);
		
		if (user != null) {
			
			String token = tokenManager.issueToken(user);
			
			response.addHeader("adventure-token", token);
			response.addHeader("Access-Control-Expose-Headers", "adventure-token");
			response.setStatus(200);
			
			return user;
		} else {
			response.setStatus(401);
			return null;
		}
		
	}
	
	

}