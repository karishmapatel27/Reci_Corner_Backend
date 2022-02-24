package com.fdmgroup.RecipeManagementStstem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import com.fdmgroup.RecipeManagementStstem.model.Category;
import com.fdmgroup.RecipeManagementStstem.model.RecipeContent;
import com.fdmgroup.RecipeManagementStstem.model.User;
import com.fdmgroup.RecipeManagementStstem.repository.RecipeContentRepository;
import com.fdmgroup.RecipeManagementStstem.repository.UserRepository;

@SpringBootTest
public class RecipeContentServiceTest {
	
	@Autowired
	private RecipeContentService recipeContentService;
	
	@MockBean 
	private RecipeContentRepository mockRecipeContentRepo;
	
	@MockBean 
	private UserRepository userRepo;
	
	@Test
	public void that_save_saves_a_recipe_content_to_the_repository() {
		User user = new User("test@gmail.com", "JMiller", "testPassword", "Jolly", "Miller");
		Category category = new Category("Main");
		
		RecipeContent recipeContent = new RecipeContent("Pasta","https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg", "https://www.youtube.jjhdjsd", "added extra cheese", user, category);
		when(mockRecipeContentRepo.save(ArgumentMatchers.any(RecipeContent.class))).thenReturn(recipeContent);
		
		RecipeContent createdRecipeContent = recipeContentService.save(recipeContent, user.getUserName());
		
		verify(mockRecipeContentRepo).save(recipeContent); 
		assertEquals(recipeContent.getRecipeName(), createdRecipeContent.getRecipeName());
	}
	
	@Test
	public void that_getAll_returns_all_recipeContent_from_repository() {
		User user = new User();
		Category category = new Category();
		List<RecipeContent> recipeContentList = new ArrayList<>();
		
		RecipeContent recipe1 = new RecipeContent("Pasta","https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg", "https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s", "added extra cheese", user, category);
		RecipeContent recipe2 = new RecipeContent("chicken curry","https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg", "https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s", "replaced normal cream with coconut cream", user, category);
		
		recipeContentList.add(recipe1);
		recipeContentList.add(recipe2);
		
		when(mockRecipeContentRepo.findAll()).thenReturn(recipeContentList);
		
		List<RecipeContent> foundList = recipeContentService.getAll();
		
		assertEquals(2, foundList.size());
		assertEquals(foundList, recipeContentList);
		verify(mockRecipeContentRepo, times(1)).findAll();
	}
	
	@Test
	public void that_findById_returns_found_recipeContent_from_repository() {
		User user = new User();
		Category category = new Category();
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setId(1);
		
		when(mockRecipeContentRepo.findById(recipeContent.getId())).thenReturn(Optional.of(recipeContent));

		RecipeContent expectedRecipeContent = recipeContentService.findById(1);
		
		assertEquals(expectedRecipeContent , recipeContent);
		verify(mockRecipeContentRepo, times(1)).findById(1);
	} 
	
	@Test
	public void that_deleteById_delete_correct_recipeContent_from_repository() {
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setId(1);
		
		when(mockRecipeContentRepo.findById(recipeContent.getId())).thenReturn(Optional.of(recipeContent));

		recipeContentService.deleteById(recipeContent.getId());
		verify(mockRecipeContentRepo, times(1)).deleteById(recipeContent.getId());;
	}
	 
	@Test
	public void that_update_update_recipeContent_from_repository() {
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setId(1);
		recipeContent.setModification("Added extra chilli");
		
		recipeContent.setModification("Added extra chesse");
	
		recipeContentService.update(recipeContent);
		
		assertEquals("Added extra chesse", recipeContent.getModification());
		verify(mockRecipeContentRepo).save(recipeContent);
	}
	
}
