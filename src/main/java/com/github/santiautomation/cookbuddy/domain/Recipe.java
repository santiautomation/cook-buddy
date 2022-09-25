package com.github.santiautomation.cookbuddy.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE recipe SET status = false WHERE id=?")
@Where(clause = "status=true")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String details;

    @ManyToMany(targetEntity = Ingredient.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  //  @OneToMany(mappedBy = "pk.ingredient", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients;

    @ManyToOne
    @JoinColumn(name="subcategory_id")
    private Subcategory subcategory;

    private boolean status = Boolean.TRUE;

    public Recipe(long recipeId) {
        this.id = recipeId;
    }
}
