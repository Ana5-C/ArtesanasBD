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

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Payment> getAll() {
        return paymentService.getAll();
    }

    @GetMapping("/{idPayment}")
    public ResponseEntity<Payment> getIdPayment(@PathVariable Long idPayment){
        Payment payment = paymentService.getIdPayment(idPayment);
        return new ResponseEntity<Payment>(payment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Payment payment){
        paymentService.save(payment);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    @PutMapping("/{idPayment}")
    public ResponseEntity<?> update(@RequestBody Payment payment, @PathVariable Long idPayment){
        Payment auxPayment = paymentService.getIdPayment(idPayment);
        payment.setIdPayment(auxPayment.getIdPayment());
        paymentService.save(payment);
        return new ResponseEntity<>("Update", HttpStatus.OK);
    }

    @DeleteMapping("/{idPayment}")
    public ResponseEntity<?> delete(@PathVariable Long idPayment){
        paymentService.delete(idPayment);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

}
