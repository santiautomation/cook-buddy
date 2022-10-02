package com.github.santiautomation.cookbuddy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.santiautomation.cookbuddy.domain.Ingredient;
import com.github.santiautomation.cookbuddy.domain.Subcategory;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeFilter {

    private Subcategory subcategory;
    private Set<Ingredient> ingredients;

    private boolean isVegan = false;
    private boolean isVegetarian = false;
    private boolean keto = false;
    private boolean celiac = false;

    private String language = "es"; // Spanish only for now.

}
