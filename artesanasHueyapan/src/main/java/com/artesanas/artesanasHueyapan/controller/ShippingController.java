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
import com.artesanas.artesanasHueyapan.model.Shipping;
import com.artesanas.artesanasHueyapan.service.ShippingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/shipping")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT })
@Tag(name = "Shipping", description = "Provides methods for managin shipping types")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

     @Operation(summary = "Get all shipping types")
    @ApiResponse(responseCode = "100", description = "Found shipping types", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Shipping.class))) })
   
    @GetMapping
    public List<Shipping> getAll() {
        return shippingService.getAll();
    }

    @Operation(summary = "Get a shipping by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Shipping found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Shipping.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
			@ApiResponse(responseCode = "404", description = "Shipping not found", content = @Content) })
    @GetMapping("/{idShipping}")
    public ResponseEntity<Shipping> getIdShipping(@PathVariable Long idShipping) {
        Shipping shipping = shippingService.getIdShipping(idShipping);
        return new ResponseEntity<Shipping>(shipping, HttpStatus.OK);
      
    }

    @Operation(summary = "Register a new shipping")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Shipping shipping) {
        shippingService.save(shipping);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    @Operation(summary = "Update a shipping")
    @PutMapping("/{idShipping}")
    public ResponseEntity<?> update(@RequestBody Shipping shipping, @PathVariable Long idShipping) {
        Shipping auxShipping = shippingService.getIdShipping(idShipping);
        shipping.setIdShipping(auxShipping.getIdShipping());
        shippingService.save(shipping);
        return new ResponseEntity<String>("Update", HttpStatus.OK);
    }

    @Operation(summary = "Delete a shipping")
    @DeleteMapping("/{idShipping}")
    public ResponseEntity<?> delete(@PathVariable Long idShipping){
        shippingService.delete(idShipping);
        return new ResponseEntity<>("Delete", HttpStatus.OK);
      
    }
}
