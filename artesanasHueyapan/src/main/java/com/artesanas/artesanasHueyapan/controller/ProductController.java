package com.artesanas.artesanasHueyapan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.artesanas.artesanasHueyapan.model.Product;
import com.artesanas.artesanasHueyapan.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
@Tag(name = "Products", description = "Provides methods for managing products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get all products")
    @ApiResponse(responseCode = "200", description = "Found products", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class))) })
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product found", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid product id supplied", content = @Content),
        @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> getById(@PathVariable Long idProduct) {
        Optional<Product> productOptional = productService.getById(idProduct);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                              .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "201", description = "Product created", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) })
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product newProduct) {
        Product savedProduct = productService.save(newProduct);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a product by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product updated"),
        @ApiResponse(responseCode = "404", description = "Product not found") })
    @PutMapping("/{idProduct}")
    public ResponseEntity<Void> update(@PathVariable Long idProduct, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productService.getById(idProduct);
        if (existingProduct.isPresent()) {
            updatedProduct.setIdProduct(existingProduct.get().getIdProduct());
            productService.save(updatedProduct);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete a product by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Product deleted"),
        @ApiResponse(responseCode = "404", description = "Product not found") })
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> delete(@PathVariable Long idProduct) {
        if (productService.getById(idProduct).isPresent()) {
            productService.delete(idProduct);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
