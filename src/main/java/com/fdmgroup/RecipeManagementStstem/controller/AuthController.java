package com.fdmgroup.RecipeManagementStstem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.RecipeManagementStstem.model.LoginCredentials;
import com.fdmgroup.RecipeManagementStstem.model.User;
import com.fdmgroup.RecipeManagementStstem.repository.UserRepository;
import com.fdmgroup.RecipeManagementStstem.security.JWTUtil;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public Map<String, Object> registerHandler(@RequestBody User user) {
		String encodedPass = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPass);
		user = userRepo.save(user);
		String token = jwtUtil.generateToken(user.getUserName());
		return Collections.singletonMap("jwttoken", token);
	} 

	@PostMapping("/login")
	public Map<String, Object> loginHandler(@RequestBody LoginCredentials body) {
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getUserName(), body.getPassword());

			authManager.authenticate(authInputToken);

			String token = jwtUtil.generateToken(body.getUserName());

			return Collections.singletonMap("jwttoken", token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Invalid Login Credentials"); 
		}
	}

}
