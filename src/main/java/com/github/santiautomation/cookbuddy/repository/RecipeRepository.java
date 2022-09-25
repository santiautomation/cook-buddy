package com.github.santiautomation.cookbuddy.repository;

import com.github.santiautomation.cookbuddy.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findByName(String name);
}
