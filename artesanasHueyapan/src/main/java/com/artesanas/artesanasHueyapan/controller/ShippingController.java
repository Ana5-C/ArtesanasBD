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


@RestController
@RequestMapping("/shipping")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @GetMapping
    public List<Shipping> getAll() {
        return shippingService.getAll();
    }

    @GetMapping("/{idShipping}")
    public ResponseEntity<Shipping> getIdShipping(@PathVariable Long idShipping) {
        Shipping shipping = shippingService.getIdShipping(idShipping);
        return new ResponseEntity<Shipping>(shipping, HttpStatus.OK);
      
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Shipping shipping) {
        shippingService.save(shipping);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    @PutMapping("/{idShipping}")
    public ResponseEntity<?> update(@RequestBody Shipping shipping, @PathVariable Long idShipping) {
        Shipping auxShipping = shippingService.getIdShipping(idShipping);
        shipping.setIdShipping(auxShipping.getIdShipping());
        shippingService.save(shipping);
        return new ResponseEntity<String>("Update", HttpStatus.OK);
    }

    @DeleteMapping("/{idShipping}")
    public ResponseEntity<?> delete(@PathVariable Long idShipping){
        shippingService.delete(idShipping);
        return new ResponseEntity<>("Delete", HttpStatus.OK);
      
    }
}
