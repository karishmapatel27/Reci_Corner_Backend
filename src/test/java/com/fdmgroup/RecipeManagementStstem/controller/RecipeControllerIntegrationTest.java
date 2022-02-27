package com.fdmgroup.RecipeManagementStstem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdmgroup.RecipeManagementStstem.model.Category;
import com.fdmgroup.RecipeManagementStstem.model.RecipeContent;
import com.fdmgroup.RecipeManagementStstem.model.User;
import com.fdmgroup.RecipeManagementStstem.repository.RecipeContentRepository;
import com.fdmgroup.RecipeManagementStstem.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Import(RecipeController.class)
public class RecipeControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private RecipeContentRepository recipeContentRepo;

	@Autowired
	private UserRepository userRepo;

	@Test
	@Transactional
	@WithMockUser(username = "Username", password = "Password")
	public void testSaveRecipeContent() throws Exception {
		User user = new User();
		user.setEmail("user@test.com");
		user.setUserName("Username");
		user.setPassword("Pass");
		user.setFirstName("J");
		user.setLastName("M");

		userRepo.save(user);

		Category category = new Category();
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setRecipeName("pasta");
		recipeContent.setImageUrl(
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg");
		recipeContent.setYoutubeLink("https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s");
		recipeContent.setModification("added extra...");
		recipeContent.setCategory(category);
		recipeContent.setUser(user);

		ObjectMapper objectMapper = new ObjectMapper();
		String reqjson = objectMapper.writeValueAsString(recipeContent);

		mvc.perform(MockMvcRequestBuilders.post("/api/user/createRecipeContent").contentType(MediaType.APPLICATION_JSON)
				.content(reqjson)).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	@Transactional
	@WithMockUser(username = "Username", password = "Password")
	public void testGetAllRecipeContent() throws Exception {
		User user = new User();
		Category category = new Category();
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setRecipeName("pasta");
		recipeContent.setImageUrl(
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg");
		recipeContent.setYoutubeLink("https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s");
		recipeContent.setModification("added extra...");
		recipeContent.setCategory(category);
		recipeContent.setUser(user);

		mvc.perform(MockMvcRequestBuilders.get("/api/user/recipeContent")).andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	@Transactional
	@WithMockUser(username = "Username", password = "Password")
	public void testGetRecipeContentById() throws Exception {
		User user1 = new User();
		Category category1 = new Category();
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setRecipeName("pasta");
		recipeContent.setImageUrl(
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg");
		recipeContent.setYoutubeLink("https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s");
		recipeContent.setModification("added extra...");
		recipeContent.setCategory(category1);
		recipeContent.setUser(user1);

		recipeContentRepo.save(recipeContent);

		mvc.perform(MockMvcRequestBuilders.get("/api/user/recipeContent/" + recipeContent.getId()))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	@Transactional
	@WithMockUser(username = "Username", password = "Password")
	public void testUpdateRecipeContent() throws Exception {
		User user = new User();
		Category category = new Category();
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setRecipeName("pasta");
		recipeContent.setImageUrl(
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg");
		recipeContent.setYoutubeLink("https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s");
		recipeContent.setModification("added extra...");
		recipeContent.setCategory(category);
		recipeContent.setUser(user);

		recipeContentRepo.save(recipeContent);

		recipeContent.setRecipeName("Chocolate cake");

		ObjectMapper objectMapper = new ObjectMapper();
		String reqjson = objectMapper.writeValueAsString(recipeContent);

		mvc.perform(
				MockMvcRequestBuilders.put("/api/user/update").contentType(MediaType.APPLICATION_JSON).content(reqjson))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200));

		assertEquals("Chocolate cake", recipeContent.getRecipeName());
	}

	@Test
	@Transactional
	@WithMockUser(username = "Username", password = "Password")
	public void testgetSingleRecipeContent() throws Exception {
		User user1 = new User();
		Category category1 = new Category();
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setRecipeName("pasta");
		recipeContent.setImageUrl(
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg");
		recipeContent.setYoutubeLink("https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s");
		recipeContent.setModification("added extra...");
		recipeContent.setCategory(category1);
		recipeContent.setUser(user1);

		recipeContentRepo.save(recipeContent);

		mvc.perform(MockMvcRequestBuilders.get("/api/user/update/" + recipeContent.getId()))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	@Transactional
	@WithMockUser(username = "Username", password = "Password")
	public void testProcessDeleteRecipeContent() throws Exception {
		User user = new User();
		Category category = new Category();
		RecipeContent recipeContent = new RecipeContent();
		recipeContent.setRecipeName("Noodles");
		recipeContent.setImageUrl(
				"https://images.immediate.co.uk/production/volatile/sites/30/2021/04/Pasta-alla-vodka-f1d2e1c.jpg");
		recipeContent.setYoutubeLink("https://www.youtube.com/watch?v=jwyge5daKUQ&t=68s");
		recipeContent.setModification("added extra chilli sauce");
		recipeContent.setCategory(category);
		recipeContent.setUser(user);

		recipeContentRepo.save(recipeContent);

		mvc.perform(MockMvcRequestBuilders.delete("/api/user/delete/" + recipeContent.getId())).andExpect(MockMvcResultMatchers.status().is(200));
	}

}
