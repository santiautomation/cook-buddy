package com.github.santiautomation.cookbuddy.services;

import com.github.santiautomation.cookbuddy.domain.Ingredient;
import com.github.santiautomation.cookbuddy.domain.Recipe;
import com.github.santiautomation.cookbuddy.domain.RecipeIngredient;
import com.github.santiautomation.cookbuddy.domain.RecipeIngredientId;
import com.github.santiautomation.cookbuddy.dto.IngredientDTO;
import com.github.santiautomation.cookbuddy.dto.RecipeDTO;
import com.github.santiautomation.cookbuddy.dto.RecipeFilter;
import com.github.santiautomation.cookbuddy.mappers.RecipeMapper;
import com.github.santiautomation.cookbuddy.repository.IngredientRepository;
import com.github.santiautomation.cookbuddy.repository.RecipeIngredientRepository;
import com.github.santiautomation.cookbuddy.repository.RecipeRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

    private RecipeMapper mapper = Mappers.getMapper(RecipeMapper.class);

    public List<RecipeDTO> getAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        LOG.info("getAllRecipes request, findAll returned {} rows", recipes.size());
        List<RecipeDTO> dtos = new ArrayList<>();

        for (Recipe r: recipes) {
            dtos.add(mapper.recipeToRecipeDTO(r));
        }

        return  dtos;
    }

    public RecipeDTO getRecipeById(Long recipeId) {
        return mapper.recipeToRecipeDTO(recipeRepository.getReferenceById(recipeId));
    }

    public String deleteRecipe(Long recipeId) {
        LOG.info("Disabling Recipe ID {}", recipeId);

        if (!recipeRepository.existsById(recipeId)) {
            return "Recipe with ID '" + recipeId + "' does not exists.";
        }

        recipeRepository.deleteById(recipeId);
        return "Recipe with ID '" + recipeId + "' deleted successfully.";
    }

    public RecipeDTO createRecipe(RecipeDTO recipe) {
        Recipe rec = mapper.recipeDTOToRecipe(recipe);

        if (null == recipeRepository.findByName(rec.getName())) {
            LOG.info("Creating new Recipe {} ", rec);
            recipeRepository.save(rec);
        } else {
            LOG.info("Recipe {} already exists !", rec);
        }

        rec = recipeRepository.findByName(recipe.getName());
        return mapper.recipeToRecipeDTO(rec);
    }

    public String addIngredient(Long recipeId, Long ingredientId) {
        LOG.info("Adding Ingredient with ID {} to the Recipe ID {}", ingredientId, recipeId);

        if (!recipeRepository.existsById(recipeId)) {
            return "Recipe with ID '" + recipeId + "' does not exists!";
        }

        if (!ingredientRepository.existsById(ingredientId)) {
            return "Ingredient with ID '" + ingredientId + "' does not exists!";
        }

        RecipeIngredient ri = new RecipeIngredient();
        ri.setStatus(true);

        RecipeIngredientId pk = new RecipeIngredientId();
        pk.setIngredient(new Ingredient(ingredientId));
        pk.setRecipe(new Recipe(recipeId));
        ri.setPk(pk);

        recipeIngredientRepository.save(ri);

        return "Ingredient added successfully";
    }

    public List<RecipeDTO> getRecipesByIngredientList(RecipeFilter recipeFilter) {
        LOG.info("getRecipesByIngredientList request  with List of Ingredients {}", recipeFilter);

        List<Long> ingredientIds = recipeFilter.getIngredients()
                .stream()
                .map(i -> i.getId()).collect(Collectors.toList());

        List<Recipe> recipes = recipeRepository.findByIngredients(ingredientIds);

        LOG.info("Result before Category Filter: " + recipes);

        if (recipeFilter.getSubcategory() != null) {
            recipes = recipes
                    .stream()
                    .filter(r -> r.getSubcategory().getSubcategoryId()
                            .equals(recipeFilter.getSubcategory().getSubcategoryId()))
                    .collect(Collectors.toList());
            LOG.info("Result after Category Filter: " + recipes);
        }

        List<RecipeDTO> dtos = new ArrayList<>();

        for (Recipe r: recipes) {
            dtos.add(mapper.recipeToRecipeDTO(r));
        }

        return  dtos;
    }
}
