package com.artesanas.artesanasHueyapan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artesanas.artesanasHueyapan.model.Address;
import com.artesanas.artesanasHueyapan.repository.AddressRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public List<Address>getAll(){
        return addressRepository.findAll();
    }
    
    public void save(Address address){
        addressRepository.save(address);
    }

    public Address getIdAddress(Long idAddress){
        return addressRepository.findById(idAddress).get();
    }

    public void delete(Long idAddress){
        addressRepository.deleteById(idAddress);
    }
}
