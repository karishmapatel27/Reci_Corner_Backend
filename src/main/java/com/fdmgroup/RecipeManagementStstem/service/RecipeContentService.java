package com.fdmgroup.RecipeManagementStstem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.RecipeManagementStstem.model.RecipeContent;
import com.fdmgroup.RecipeManagementStstem.model.User;
import com.fdmgroup.RecipeManagementStstem.repository.RecipeContentRepository;
import com.fdmgroup.RecipeManagementStstem.repository.UserRepository;

@Service
public class RecipeContentService {
	
	private RecipeContentRepository recipeContentRepo;
	
	private UserRepository userRepo;
	
	@Autowired
	public RecipeContentService(RecipeContentRepository recipeContentRepo, UserRepository userRepo) {
		super();
		this.recipeContentRepo = recipeContentRepo;
		this.userRepo = userRepo;
	}

	public List<RecipeContent> getAll() {
		return recipeContentRepo.findAll();
	}
	
	public RecipeContent findById(int id) {
		return recipeContentRepo.findById(id).get();
	}
	
	public RecipeContent save(RecipeContent recipeContent, String userName) {
		Optional<User> foundUser = userRepo.findByUserName(userName);
		recipeContent.setUser(foundUser.orElse(null));
		return recipeContentRepo.save(recipeContent);
	}
	
	public RecipeContent update(RecipeContent recipeContent) {
		return recipeContentRepo.save(recipeContent);
	}
	
	public void deleteById(int id) {
		 recipeContentRepo.deleteById(id);
	}
	
}