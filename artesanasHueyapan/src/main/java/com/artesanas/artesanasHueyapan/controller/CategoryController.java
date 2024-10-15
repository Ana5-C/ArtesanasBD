package com.artesanas.artesanasHueyapan.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.artesanas.artesanasHueyapan.model.Category;
import com.artesanas.artesanasHueyapan.repository.CategoryRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/category")
@Tag(name = "Categories", description = "Provides methods for managing categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories (READ)
    @Operation(summary = "Get all categories")
    @ApiResponse(responseCode = "200", description = "Found categories", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
    })
    @GetMapping
    public ResponseEntity<Iterable<Category>> findAll() {
        Iterable<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    // Get category by ID (READ)
    @Operation(summary = "Get category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
            }),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @GetMapping("/{idCategory}")
    public ResponseEntity<Category> findById(@PathVariable Long idCategory) {
        return categoryRepository.findById(idCategory)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new category (CREATE)
    @Operation(summary = "Create a new category")
    @ApiResponse(responseCode = "201", description = "Category created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
    })
    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody Category newCategory, UriComponentsBuilder ucb) {
        Category savedCategory = categoryRepository.save(newCategory);
        URI uri = ucb.path("/category/{idCategory}")
                .buildAndExpand(savedCategory.getIdCategory())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    // Update a category (UPDATE)
    @Operation(summary = "Update a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
            }),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @PutMapping("/{idCategory}")
    public ResponseEntity<Category> update(@PathVariable Long idCategory,
                                           @Validated @RequestBody Category updatedCategory) {
        return categoryRepository.findById(idCategory)
                .map(existingCategory -> {
                    updateCategory(existingCategory, updatedCategory); // Call private method to update
                    Category savedCategory = categoryRepository.save(existingCategory);
                    return ResponseEntity.ok(savedCategory);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a category (DELETE)
    @Operation(summary = "Delete a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> delete(@PathVariable Long idCategory) {
        if (categoryRepository.existsById(idCategory)) {
            categoryRepository.deleteById(idCategory);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Private method to update category fields
    private void updateCategory(Category existingCategory, Category updatedCategory) {
        existingCategory.setName(updatedCategory.getName());
    }
}
