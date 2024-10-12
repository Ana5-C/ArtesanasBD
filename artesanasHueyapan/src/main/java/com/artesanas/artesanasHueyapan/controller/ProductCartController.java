package com.artesanas.artesanasHueyapan.controller;

import java.util.List;

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

import com.artesanas.artesanasHueyapan.model.ProductCart;
import com.artesanas.artesanasHueyapan.services.ProductCartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/productCart")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@Tag(name = "Product Cart", description = "provides methods")
public class ProductCartController {
    @Autowired
    private ProductCartService productCartService;

    @Operation(summary = "Get all product carts")
    @ApiResponse(responseCode = "200", description = "Found product carts", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductCart.class)))
    })
    @GetMapping
    public List<ProductCart> getAll() {
        return productCartService.getAll();
    }

    @Operation(summary = "Get a product cart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product cart found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductCart.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product cart not found", content = @Content)
    })
    @GetMapping("/{idProductCart}")
    public ResponseEntity<?> getByIdProductCart(@PathVariable Long idProductCart) {
        ProductCart productCart = productCartService.getByProductCart(idProductCart);
        return new ResponseEntity<ProductCart>(productCart, HttpStatus.OK);
    }

    @Operation(summary = "Register a new product cart")
    @PostMapping
    public ResponseEntity<?> created(@RequestBody ProductCart productCart) {
        productCartService.save(productCart);
        return new ResponseEntity<String>("save", HttpStatus.OK);
    }

    @Operation(summary = "Update a product cart")
    @PutMapping("/{idProductCart}")
    public ResponseEntity<?> update(@RequestBody ProductCart productCart, @PathVariable Long idProductCart) {
        ProductCart auxProductCart = productCartService.getByProductCart(idProductCart);
        productCart.setIdProductCart(auxProductCart.getIdProductCart());
        productCartService.save(productCart);
        return new ResponseEntity<String>("updated", HttpStatus.OK);
    }

    @Operation(summary = "Delete a product cart")
    @DeleteMapping("/{idProductCart}")
    public ResponseEntity<?> delete(@PathVariable Long idProductCart) {
        productCartService.delete(idProductCart);
        return new ResponseEntity<String>("Deleted", HttpStatus.OK);
    }
}
