package com.artesanas.artesanasHueyapan.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.artesanas.artesanasHueyapan.model.Payment;
import com.artesanas.artesanasHueyapan.repository.PaymentRepository;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    
    @Autowired
    PaymentRepository paymentRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Payment>> findAll(){
        return ResponseEntity.ok(paymentRepository.findAll());
    }

    @GetMapping("/{idPayment}")
    public ResponseEntity<Payment> findById(@PathVariable Long idPayment){
        Optional<Payment> paymentOptional = paymentRepository.findById(idPayment);
        if(paymentOptional.isPresent()){
            return ResponseEntity.ok(paymentOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Payment newPayment, UriComponentsBuilder ucb){
        Payment savedPayment = paymentRepository.save(newPayment);
        URI uri = ucb
                .path("payment/{idPayment}")
                .buildAndExpand(savedPayment.getIdPayment())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idPayment}")
    public ResponseEntity<Void> update(@PathVariable Long idPayment, @RequestBody Payment paymentAct){
        Payment paymentAnt = paymentRepository.findById(idPayment).get();
        if (paymentAnt != null){
            paymentAct.setIdPayment(paymentAnt.getIdPayment());
            paymentRepository.save(paymentAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idPayment}")
    public ResponseEntity<Void> delete(@PathVariable Long idPayment){
        if(paymentRepository.findById(idPayment).get() !=null){
            paymentRepository.deleteById(idPayment);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
