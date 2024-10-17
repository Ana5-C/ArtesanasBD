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
import com.artesanas.artesanasHueyapan.model.Payment;
import com.artesanas.artesanasHueyapan.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "Payment", description = "Provides methods for managin payment types")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    
    @Operation(summary = "Get all payment types")
    @ApiResponse(responseCode = "100", description = "Found payment types", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Payment.class))) })
   
    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAll();
    }

    @Operation(summary = "Get a payment by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Payment found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
			@ApiResponse(responseCode = "404", description = "Payment not found", content = @Content) })
    @GetMapping("/{idPayment}")
    public ResponseEntity<Payment> getIdPayment(@PathVariable Long idPayment){
        Payment payment = paymentService.getIdPayment(idPayment);
        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    @Operation(summary = "Register a new payment")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Payment payment){
        paymentService.save(payment);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    @Operation(summary = "Update a payment")
    @PutMapping("/{idPayment}")
    public ResponseEntity<?> update(@RequestBody Payment payment, @PathVariable Long idPayment){
        Payment auxPayment = paymentService.getIdPayment(idPayment);
        payment.setIdPayment(auxPayment.getIdPayment());
        paymentService.save(payment);
        return new ResponseEntity<>("Update", HttpStatus.OK);
    }

    @Operation(summary = "Delete a payment")
    @DeleteMapping("/{idPayment}")
    public ResponseEntity<?> delete(@PathVariable Long idPayment){
        paymentService.delete(idPayment);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

}
