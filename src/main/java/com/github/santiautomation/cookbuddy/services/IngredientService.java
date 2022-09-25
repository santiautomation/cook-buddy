package com.github.santiautomation.cookbuddy.services;

import com.github.santiautomation.cookbuddy.domain.Ingredient;
import com.github.santiautomation.cookbuddy.domain.Recipe;
import com.github.santiautomation.cookbuddy.dto.IngredientDTO;
import com.github.santiautomation.cookbuddy.dto.RecipeDTO;
import com.github.santiautomation.cookbuddy.mappers.IngredientMapper;
import com.github.santiautomation.cookbuddy.mappers.RecipeMapper;
import com.github.santiautomation.cookbuddy.repository.IngredientRepository;
import com.github.santiautomation.cookbuddy.repository.RecipeIngredientRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    private IngredientMapper mapper = Mappers.getMapper(IngredientMapper.class);

    private static final Logger LOG = LoggerFactory.getLogger(RecipeService.class);

    public List<IngredientDTO> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        LOG.info("getAllIngredients request, findAll returned {} rows", ingredients.size());
        List<IngredientDTO> dtos = new ArrayList<>();

        for (Ingredient i: ingredients) {
            dtos.add(mapper.ingredientToIngredientDTO(i));
        }

        return  dtos;
    }

    public IngredientDTO createIngredient(IngredientDTO ingredient) {
        Ingredient ing = mapper.ingredientDTOToIngredient(ingredient);

        if (null == ingredientRepository.findByName(ingredient.getName())) {
            LOG.info("Creating new Ingredient {} ", ingredient);
            ingredientRepository.save(ing);
        } else {
            LOG.info("Ingredient {} already exists !", ingredient);
        }

        ing = ingredientRepository.findByName(ingredient.getName());
        return mapper.ingredientToIngredientDTO(ing);
    }

    public String update(Long ingredientId, String name) {
        Ingredient ing = new Ingredient(ingredientId, name);

        if (ingredientRepository.existsById(ingredientId)) {
            ingredientRepository.save(ing);
            return "Ingredient updated successfully";
        }

        return "Ingredient ID '" + ingredientId  + "' not found";
    }
}
