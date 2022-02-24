package com.fdmgroup.RecipeManagementStstem.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fdmgroup.RecipeManagementStstem.model.User;
import com.fdmgroup.RecipeManagementStstem.repository.UserRepository;
import com.fdmgroup.RecipeManagementStstem.security.JWTUtil;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {
	
	
	private AuthController authController;
	
	@Autowired
	private UserRepository userRepo;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private JWTUtil jwtUtil; 
	
	
	@Test
	public void registerHandler_save_user_in_the_repository() {
		User user = new User();
		user.setId((long) 1);
	    user.setEmail("John@gmail.com");
	    user.setUserName("JDoe123");
	    user.setPassword("password");
	    user.setFirstName("John");
	    user.setLastName("Doe");
	    
	    when(passwordEncoder.encode(user.getPassword())).thenReturn("$1di20dn39");
	     
	    User savedUser = userRepo.save(user);
	    		
	    verify(userRepo).save(user);
		
	} 

}
