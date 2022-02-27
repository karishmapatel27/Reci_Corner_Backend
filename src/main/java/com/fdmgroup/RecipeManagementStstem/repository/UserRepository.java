package com.fdmgroup.RecipeManagementStstem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.RecipeManagementStstem.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

		public Optional<User> findByUserName(String userName);
	
}
