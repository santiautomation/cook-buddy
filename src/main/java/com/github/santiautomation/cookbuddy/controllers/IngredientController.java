package com.github.santiautomation.cookbuddy.controllers;

import com.github.santiautomation.cookbuddy.dto.IngredientDTO;
import com.github.santiautomation.cookbuddy.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "ingredient")
@RequestMapping("ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getIngredients() {
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @PostMapping("/create")
    public ResponseEntity<IngredientDTO> createIngredient(@RequestBody IngredientDTO ing) {
        return ResponseEntity.ok(ingredientService.createIngredient(ing));
    }

    @PutMapping("/{ingredientId}/{name}")
    public ResponseEntity<String> updateIngredientName(@PathVariable Long ingredientId, @PathVariable String name) {
        return ResponseEntity.ok(ingredientService.update(ingredientId, name));
    }
}
