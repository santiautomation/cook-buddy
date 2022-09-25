package com.github.santiautomation.cookbuddy.dto;

import com.github.santiautomation.cookbuddy.domain.Category;
import com.github.santiautomation.cookbuddy.domain.Ingredient;
import com.github.santiautomation.cookbuddy.domain.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

    private Long id;
    private String name;
    private String description;
    private String details;

    private List<Ingredient> ingredients;

    private Subcategory subcategory;
}
