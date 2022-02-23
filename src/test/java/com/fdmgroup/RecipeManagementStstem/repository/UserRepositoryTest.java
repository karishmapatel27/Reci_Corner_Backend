package com.fdmgroup.RecipeManagementStstem.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.fdmgroup.RecipeManagementStstem.model.User;
import com.fdmgroup.RecipeManagementStstem.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE )
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
	    user.setEmail("John@gmail.com");
	    user.setUserName("JDoe123");
	    user.setPassword("password");
	    user.setFirstName("John");
	    user.setLastName("Doe");
	     
	    User savedUser = userRepo.save(user);
	     
	    User existUser = entityManager.find(User.class, savedUser.getId());
	     
	    assertEquals(user.getEmail(), existUser.getEmail());
	}

}
