package com.github.santiautomation.cookbuddy.controllers;

import com.github.santiautomation.cookbuddy.dto.RecipeDTO;
import com.github.santiautomation.cookbuddy.services.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "recipe")
@RequestMapping("recipe")
public class RecipeController {

    private static final Logger LOG = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> getRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Long recipeId) {
        return ResponseEntity.ok(recipeService.getRecipeById(recipeId));
    }

    @DeleteMapping("/delete/{recipeId}")
    public ResponseEntity<String> deleteRecipeById(@PathVariable Long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId));
    }

    @PostMapping("/create")
    public ResponseEntity<RecipeDTO> createRecipe(@Validated @RequestBody RecipeDTO recipe) {
        return ResponseEntity.ok(recipeService.createRecipe(recipe));
    }

    @PostMapping("/{recipeId}/ingredients/{ingredientId}")
    public ResponseEntity<String> addIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId) {
        return ResponseEntity.ok(recipeService.addIngredient(recipeId, ingredientId));
    }

}
