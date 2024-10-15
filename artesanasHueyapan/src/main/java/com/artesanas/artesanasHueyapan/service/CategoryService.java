package com.artesanas.artesanasHueyapan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import com.artesanas.artesanasHueyapan.repository.CategoryRepository; // Ensure this repository exists
import com.artesanas.artesanasHueyapan.model.Category; // Ensure this model exists

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    // Method to get all categories
    public List<Category> getAll() {
        return repository.findAll(); // Return a list of all categories
    }

    // Method to get a category by its ID
    public Optional<Category> getById(Long idCategory) {
        return repository.findById(idCategory); // Return an Optional
    }

    // Method to save a new category
    public Category save(Category category) {
        return repository.save(category); // Return the saved category
    }

    // Method to delete a category by its ID
    public void delete(Long idCategory) {
        repository.deleteById(idCategory);
    }
}
