package com.fdmgroup.RecipeManagementStstem.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "RecipeContent")
public class RecipeContent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recipe_content_id")
	private int id;

	@Column(nullable = false, length = 50)
	private String recipeName;

	@Column(nullable = false, name = "image_url")
	private String imageUrl;

	@Column(nullable = false)
	private String youtubeLink;

	private String modification;

	@ManyToOne
	@JoinColumn(name = "user_id")
//	@JsonManagedReference
	@JsonIgnore
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Category_id")
	//@JsonManagedReference
	private Category category;

	public RecipeContent() {
		super();
	}

	public RecipeContent(String recipeName, String imageUrl, String youtubeLink, String modification, User user,
			Category category) {
		super();
		this.recipeName = recipeName;
		this.imageUrl = imageUrl;
		this.youtubeLink = youtubeLink;
		this.modification = modification;
		this.user = user;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getYoutubeLink() {
		return youtubeLink;
	}

	public void setYoutubeLink(String youtubeLink) {
		this.youtubeLink = youtubeLink;
	}

	public String getModification() {
		return modification;
	}

	public void setModification(String modification) {
		this.modification = modification;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
