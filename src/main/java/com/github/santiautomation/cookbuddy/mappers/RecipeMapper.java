package com.github.santiautomation.cookbuddy.mappers;

import com.github.santiautomation.cookbuddy.domain.Recipe;
import com.github.santiautomation.cookbuddy.dto.RecipeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

    RecipeDTO recipeToRecipeDTO(Recipe recipe);
    Recipe recipeDTOToRecipe(RecipeDTO recipeDTO);
}
