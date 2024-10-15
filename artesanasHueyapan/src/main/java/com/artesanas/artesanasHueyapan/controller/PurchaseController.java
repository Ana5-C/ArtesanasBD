package com.artesanas.artesanasHueyapan.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.artesanas.artesanasHueyapan.model.Purchase; 
import com.artesanas.artesanasHueyapan.repository.BuyRepository;


@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/purchase")
@Tag(name = "Purchase", description = "Provides methods for managing purchases")
public class PurchaseController {

    @Autowired
    private BuyRepository buyRepository;

    // Get all purchases (READ)
    @Operation(summary = "Get all purchases")
    @ApiResponse(responseCode = "200", description = "Found purchases", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Purchase.class)))
    })
    @GetMapping
    public ResponseEntity<List<Purchase>> findAll() {
        List<Purchase> purchases = buyRepository.findAll();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    // Get purchase by ID (READ)
    @Operation(summary = "Get a purchase by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Purchase.class))}),
            @ApiResponse(responseCode = "404", description = "Purchase not found", content = @Content)
    })
    @GetMapping("/{idPurchase}")
    public ResponseEntity<Purchase> findById(@PathVariable Long idPurchase) {
        return buyRepository.findById(idPurchase)
                .map(purchase -> new ResponseEntity<>(purchase, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new purchase (CREATE)
    @Operation(summary = "Create a new purchase")
    @ApiResponse(responseCode = "201", description = "Purchase created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Purchase.class))
    })
    @PostMapping
    public ResponseEntity<String> create(@Validated @RequestBody Purchase newPurchase, UriComponentsBuilder ucb) {
        Purchase savedPurchase = buyRepository.save(newPurchase);
        URI uri = ucb.path("/purchase/{idPurchase}")
                .buildAndExpand(savedPurchase.getIdPurchase()) // Assuming the field is named getIdPurchase()
                .toUri();
        return ResponseEntity.created(uri).body("Saved");
    }

    // Update a purchase (UPDATE)
    @Operation(summary = "Update a purchase by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Purchase.class))}),
            @ApiResponse(responseCode = "404", description = "Purchase not found", content = @Content)
    })
    @PutMapping("/{idPurchase}")
    public ResponseEntity<String> update(@PathVariable Long idPurchase, @Validated @RequestBody Purchase updatedPurchase) {
        return buyRepository.findById(idPurchase)
                .map(existingPurchase -> {
                    existingPurchase.setDate(updatedPurchase.getDate()); // Assuming the method is setDate()
                    existingPurchase.setTotal(updatedPurchase.getTotal());
                    existingPurchase.setQuantity(updatedPurchase.getQuantity()); // Assuming the method is setQuantity()
                    existingPurchase.setPhone(updatedPurchase.getPhone()); // Assuming the method is setPhone()
                    buyRepository.save(existingPurchase);
                    return new ResponseEntity<>("Updated", HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>("Purchase not found", HttpStatus.NOT_FOUND));
    }

    // Delete a purchase (DELETE)
    @Operation(summary = "Delete a purchase by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Purchase deleted"),
            @ApiResponse(responseCode = "404", description = "Purchase not found")
    })
    @DeleteMapping("/{idPurchase}")
    public ResponseEntity<String> delete(@PathVariable Long idPurchase) {
        if (buyRepository.existsById(idPurchase)) {
            buyRepository.deleteById(idPurchase);
            return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Purchase not found", HttpStatus.NOT_FOUND);
    }
}
