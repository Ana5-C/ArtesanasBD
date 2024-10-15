package com.artesanas.artesanasHueyapan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artesanas.artesanasHueyapan.model.Product;
import com.artesanas.artesanasHueyapan.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository; // Repository for managing products

    // Method to get a product by its ID
    public Optional<Product> getById(Long idProduct) {
        return productRepository.findById(idProduct); // Returns an Optional
    }

    // Method to get all products
    public List<Product> getAll() {
        return productRepository.findAll(); // Returns a list of all products
    }

    // Method to save a new product
    public Product save(Product product) {
        return productRepository.save(product); // Returns the saved product
    }

    // Method to delete a product by its ID
    public void delete(Long idProduct) {
        productRepository.deleteById(idProduct);
    }
}
