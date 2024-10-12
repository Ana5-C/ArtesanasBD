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


@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class AddressController {

     @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/{idAddress}")
    public ResponseEntity<Address> getIdAddress(@PathVariable Long idAddress){
        Address address = addressService.getIdAddress(idAddress);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Address address){
        addressService.save(address);
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    @PutMapping("/{idAddress}")
    public ResponseEntity<?> update(@RequestBody Address address, @PathVariable Long idAddress){
        Address auxAddress = addressService.getIdAddress(idAddress);
        address.setIdAddress(auxAddress.getIdAddress());
        addressService.save(address);
        return new ResponseEntity<String>("Update", HttpStatus.OK);

    }

    @DeleteMapping("/{idAddress}")
    public ResponseEntity<?> delete(@PathVariable Long idAddress){
        addressService.delete(idAddress);
        return new ResponseEntity<String>("Delete", HttpStatus.OK);
    }

}
