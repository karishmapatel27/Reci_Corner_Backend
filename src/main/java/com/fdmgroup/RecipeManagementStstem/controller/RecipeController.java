package com.fdmgroup.RecipeManagementStstem.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.RecipeManagementStstem.model.RecipeContent;
import com.fdmgroup.RecipeManagementStstem.service.RecipeContentService;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin("*")
public class RecipeController {

	private RecipeContentService recipeContentService;

	public RecipeController(RecipeContentService recipeContentService) {
		super();
		this.recipeContentService = recipeContentService;
	}

	@PostMapping("/createRecipeContent")
	public RecipeContent saveRecipeContent(@RequestBody RecipeContent recipeContent, Authentication auth) {
		System.out.println("called");
		return recipeContentService.save(recipeContent, auth.getName());
	}

	@GetMapping("/recipeContent")
	public List<RecipeContent> getAllRecipeContents() {
		System.out.println(recipeContentService.getAll());
		return recipeContentService.getAll();
	}

	@GetMapping("/recipeContent/{id}")
	public RecipeContent getRecipeContentById(@PathVariable int id) {
		RecipeContent recipeContent = recipeContentService.findById(id);
		return recipeContent;
	}

	@GetMapping("/update/{id}")
	public RecipeContent getSingleRecipeContent(@PathVariable int id) {
		return recipeContentService.findById(id);
	}

	@PutMapping("/update")
	public void updateRecipeContent(@RequestBody RecipeContent recipeContent) {
		recipeContentService.update(recipeContent);
	}

	@DeleteMapping("/delete/{id}")
	public void processDeleteRecipeContent(@PathVariable int id) {
		recipeContentService.deleteById(id);
	}

}
