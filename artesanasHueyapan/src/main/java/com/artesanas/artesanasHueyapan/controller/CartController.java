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

import com.artesanas.artesanasHueyapan.model.Cart;
import com.artesanas.artesanasHueyapan.services.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Cart", description = "provides methods")
public class CartController {
    @Autowired
    private CartService cartService;

    @Operation(summary = "Get all cart")
    @ApiResponse(responseCode = "200", description = "Found customer", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cart.class)))
    })
    @GetMapping
    public List<Cart> getAll(){
        return cartService.getAll();
    }

    @Operation(summary = "Get a cart by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cart found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cart.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
			@ApiResponse(responseCode = "404", description = "Cart not found", content = @Content) })
    @GetMapping("/{idCart}")
    public ResponseEntity<?> getByIdCart(@PathVariable Long idCart){
        Cart cart = cartService.getByIdCart(idCart);
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }

    @Operation(summary = "Register a new cart")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cart cart){
        cartService.save(cart);
        return new ResponseEntity<String>("saved", HttpStatus.OK);
    }

    @Operation(summary = "Update a cart")
    @PutMapping("/{idCart}")
    public ResponseEntity<?> update(@RequestBody Cart cart, @PathVariable Long idCart){
        Cart auxCart = cartService.getByIdCart(idCart);
        cart.setIdCart(auxCart.getIdCart());
        cartService.save(cart);
        return new ResponseEntity<String>("updated", HttpStatus.OK);
    }

    @Operation(summary = "Delete a cart")
    @DeleteMapping("/{idCart}")
    public ResponseEntity<?> delete(@PathVariable Long idCart){
        cartService.delete(idCart);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }
}
