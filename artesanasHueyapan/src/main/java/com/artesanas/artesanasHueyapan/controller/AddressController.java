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
import com.artesanas.artesanasHueyapan.model.Address;
import com.artesanas.artesanasHueyapan.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Address", description = "Provides methods for managin address types")
public class AddressController {
     @Autowired
    private AddressService addressService;

    @Operation(summary = "Get all address types")
    @ApiResponse(responseCode = "100", description = "Found address types", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Address.class))) })
   
        @GetMapping
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @Operation(summary = "Get a address by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Address found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Address.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
			@ApiResponse(responseCode = "404", description = "Address not found", content = @Content) })
    @GetMapping("/{idAddress}")
    public ResponseEntity<Address> getIdAddress(@PathVariable Long idAddress){
        Address address = addressService.getIdAddress(idAddress);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }


    @Operation(summary = "Register a new address")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Address address){
        addressService.save(address);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    @Operation(summary = "Update a address")
    @PutMapping("/{idAddress}")
    public ResponseEntity<?> update(@RequestBody Address address, @PathVariable Long idAddress){
        Address auxAddress = addressService.getIdAddress(idAddress);
        address.setIdAddress(auxAddress.getIdAddress());
        addressService.save(address);
        return new ResponseEntity<String>("Update", HttpStatus.OK);

    }

    @Operation(summary = "Delete a address")
    @DeleteMapping("/{idAddress}")
    public ResponseEntity<?> delete(@PathVariable Long idAddress){
        addressService.delete(idAddress);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

}
