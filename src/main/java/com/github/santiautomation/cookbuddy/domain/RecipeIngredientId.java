package com.github.santiautomation.cookbuddy.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class RecipeIngredientId implements Serializable {

    @ManyToOne
    private Recipe recipe;
    @ManyToOne
    private Ingredient ingredient;
}
