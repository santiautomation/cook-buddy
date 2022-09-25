package com.github.santiautomation.cookbuddy.repository;

import com.github.santiautomation.cookbuddy.domain.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
