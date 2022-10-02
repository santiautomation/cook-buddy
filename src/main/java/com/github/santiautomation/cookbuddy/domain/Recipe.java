package com.github.santiautomation.cookbuddy.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    private Set<Ingredient> ingredients;

   // @ManyToOne
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="subcategory_id")
    private Subcategory subcategory;

    private boolean status = Boolean.TRUE;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column( columnDefinition = "uuid", updatable = false)
    private UUID imageId;

    public Recipe(long recipeId) {
        this.id = recipeId;
    }
}
