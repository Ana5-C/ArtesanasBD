package com.artesanas.artesanasHueyapan.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.artesanas.artesanasHueyapan.repository.ProductRepository;
import com.artesanas.artesanasHueyapan.model.Product;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Get all products (READ)
    @GetMapping
    public ResponseEntity<Iterable<Product>> findAll() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    // Get product by ID (READ)
    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findById(@PathVariable Long idProduct) {
        Optional<Product> productOptional = productRepository.findById(idProduct);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new product (CREATE)
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Product newProduct, UriComponentsBuilder ucb) {
        Product savedProduct = productRepository.save(newProduct);
        URI uri = ucb
                .path("/product/{idProduct}")
                .buildAndExpand(savedProduct.getIdProduct())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    // Update a product (UPDATE)
    @PutMapping("/{idProduct}")
    public ResponseEntity<Void> update(@PathVariable Long idProduct, @RequestBody Product updatedProduct) {
        Optional<Product> previousProduct = productRepository.findById(idProduct);
        if (previousProduct.isPresent()) {
            updatedProduct.setIdProduct(previousProduct.get().getIdProduct());
            productRepository.save(updatedProduct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a product (DELETE)
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> delete(@PathVariable Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        if (product.isPresent()) {
            productRepository.deleteById(idProduct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
