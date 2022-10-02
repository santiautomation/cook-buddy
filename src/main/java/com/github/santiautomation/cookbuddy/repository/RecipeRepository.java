package com.github.santiautomation.cookbuddy.repository;

import com.github.santiautomation.cookbuddy.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findByName(String name);

    @Query(value = "select r.* " +
            "from recipe r " +
            "inner join recipe_ingredients ri on ri.recipe_id = r.id" +
            " where r.status = true group by r.id " +
            " having count(distinct ri.ingredients_id) = " +
            " (select count(*) from recipe_ingredients i where i.ingredients_id in :ingredientIds and " +
            " i.recipe_id = r.id)", nativeQuery = true)
    List<Recipe> findByIngredients(@Param("ingredientIds") List<Long> ingredientIds);

}
