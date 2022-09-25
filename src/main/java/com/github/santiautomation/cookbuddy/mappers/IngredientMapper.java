package com.github.santiautomation.cookbuddy.mappers;

import com.github.santiautomation.cookbuddy.domain.Ingredient;
import com.github.santiautomation.cookbuddy.dto.IngredientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    IngredientDTO ingredientToIngredientDTO(Ingredient ingredient);
    Ingredient ingredientDTOToIngredient(IngredientDTO ingredientDTO);
}
