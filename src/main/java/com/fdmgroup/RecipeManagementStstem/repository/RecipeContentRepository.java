package com.fdmgroup.RecipeManagementStstem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fdmgroup.RecipeManagementStstem.model.RecipeContent;

@Repository
public interface RecipeContentRepository extends JpaRepository<RecipeContent, Integer> {

}
