package com.artesanas.artesanasHueyapan.controller;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.artesanas.artesanasHueyapan.dto.ProductCartDTO;
import com.artesanas.artesanasHueyapan.dto.ProductRequestDTO;
import com.artesanas.artesanasHueyapan.model.Cart;
import com.artesanas.artesanasHueyapan.model.Product;
import com.artesanas.artesanasHueyapan.model.ProductCart;
import com.artesanas.artesanasHueyapan.services.CartService;
import com.artesanas.artesanasHueyapan.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class ProductController {
    @Autowired
    private ProductService productService;


    @Autowired
    private ModelMapper modelMapper;

    //encuentra todos
    /* @Operation(summary = "Get all product")
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    } */

    // encuentra por ID
    @Operation(summary = "Get a cart by ID")
    @GetMapping("/{idProduct}")
    public ResponseEntity<Product> findByIdProduct(@PathVariable Long idProduct) {
        return new ResponseEntity<Product>(productService.getByIdProduct(idProduct), HttpStatus.OK);
    }

    // crea un producto
    @Operation(summary = "Register a new product")
    @PostMapping
    public ResponseEntity<ProductRequestDTO> add(
        @RequestBody ProductRequestDTO productRequestDTO) {
            ProductRequestDTO savedProductRequestDTO = convertToDTO(productService.save(convertToEntity(productRequestDTO)));
            return new ResponseEntity<ProductRequestDTO>(savedProductRequestDTO, HttpStatus.CREATED);
    }

    public Product convertToEntity(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);  
    }

    private ProductRequestDTO convertToDTO(Product product){
        return modelMapper.map(product, ProductRequestDTO.class);
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long idProduct) {
        Product auxProduct = productService.getByIdProduct(idProduct);
        product.setIdProduct(auxProduct.getIdProduct());
        return new ResponseEntity<String>("Updated", HttpStatus.OK);
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<?> delete(@PathVariable Long idProduct){
        productService.delete(idProduct);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }
}