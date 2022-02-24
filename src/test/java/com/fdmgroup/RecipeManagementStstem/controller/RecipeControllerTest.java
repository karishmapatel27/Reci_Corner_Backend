package com.fdmgroup.RecipeManagementStstem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import com.fdmgroup.RecipeManagementStstem.model.Category;
import com.fdmgroup.RecipeManagementStstem.model.RecipeContent;
import com.fdmgroup.RecipeManagementStstem.model.User;
import com.fdmgroup.RecipeManagementStstem.service.RecipeContentService;

@ExtendWith(MockitoExtension.class)
public class RecipeControllerTest {

	@Mock
	private RecipeContentService mockRecipeContentService;

	private RecipeController recipeController;

	@Mock
	private Authentication auth;

	@BeforeEach
	public void setup() {
		recipeController = new RecipeController(mockRecipeContentService);
	}

	@Test
	public void createRecipeContent_whenPostMethod() throws Exception {
		User user = new User();
		Category category = new Category();
		RecipeContent mockRecipeContent = new RecipeContent("Pasta",
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg",
				"https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s", "added extra cheese", user, category);

		recipeController.saveRecipeContent(mockRecipeContent, auth);

		verify(mockRecipeContentService).save(mockRecipeContent, auth.getName());
	}

	@Test
	public void testGetAllRecipeContent() {

		User user = new User();
		Category category = new Category();
		RecipeContent mockRecipeContent1 = new RecipeContent("Pasta",
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg",
				"https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s", "added extra cheese", user, category);
		RecipeContent mockRecipeContent2 = new RecipeContent("Chicken",
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg",
				"https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s", "added extra cheese", user, category);
		List<RecipeContent> recipeContentList = new ArrayList<>();

		recipeContentList.add(mockRecipeContent1);
		recipeContentList.add(mockRecipeContent2);

		when(mockRecipeContentService.getAll()).thenReturn(recipeContentList);

		List<RecipeContent> result = recipeController.getAllRecipeContents();

		assertEquals(result.size(), 2);
	}

	@Test
	public void testGetRecipeContentById() {
		User user = new User();
		Category category = new Category();
		RecipeContent mockRecipeContent = new RecipeContent("Pasta",
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg",
				"https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s", "added extra cheese", user, category);
		mockRecipeContent.setId(1);

		when(mockRecipeContentService.findById(mockRecipeContent.getId())).thenReturn(mockRecipeContent);

		RecipeContent result = recipeController.getRecipeContentById(1);

		assertEquals(result.getRecipeName(), "Pasta");
	}

	@Test
	public void testGetSingleRecipeContent() {
		User user = new User();
		Category category = new Category();
		RecipeContent mockRecipeContent = new RecipeContent("Pasta",
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg",
				"https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s", "added extra cheese", user, category);
		mockRecipeContent.setId(1);

		when(mockRecipeContentService.findById(mockRecipeContent.getId())).thenReturn(mockRecipeContent);

		RecipeContent result = recipeController.getSingleRecipeContent(1);

		assertEquals(result.getRecipeName(), "Pasta");
		verify(mockRecipeContentService).findById(1);
	}

	@Test
	public void testUpdateRecipeContent() {
		User user = new User();
		Category category = new Category();
		RecipeContent mockRecipeContent = new RecipeContent("Pasta",
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg",
				"https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s", "added extra cheese", user, category);

		mockRecipeContent.setRecipeName("Chicken curry");

		when(mockRecipeContentService.update(mockRecipeContent)).thenReturn(mockRecipeContent);

		recipeController.updateRecipeContent(mockRecipeContent);

		assertEquals(mockRecipeContent.getRecipeName(), "Chicken curry");
		verify(mockRecipeContentService).update(mockRecipeContent);
	}

	@Test
	public void testProcessDeleteRecipeContent() {
		int id = 5;
		recipeController.processDeleteRecipeContent(id);
		verify(mockRecipeContentService).deleteById(id);
	}

}
