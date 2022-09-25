package com.github.santiautomation.cookbuddy.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "recipe_ingredients")
@SQLDelete(sql = "UPDATE recipe_ingredients SET status = false WHERE recipe_id=?")
@Where(clause = "status=true")
@AssociationOverrides({
        @AssociationOverride(name = "pk.recipe",
                joinColumns = @JoinColumn(name = "recipe_id")),
        @AssociationOverride(name = "pk.ingredient",
                joinColumns = @JoinColumn(name = "ingredients_id")) })
public class RecipeIngredient {

    @EmbeddedId
    private RecipeIngredientId pk = new RecipeIngredientId();
    @Column
    private boolean status = Boolean.TRUE;
}
