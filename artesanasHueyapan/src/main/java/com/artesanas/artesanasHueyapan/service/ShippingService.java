package com.artesanas.artesanasHueyapan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artesanas.artesanasHueyapan.model.Shipping;
import com.artesanas.artesanasHueyapan.repository.ShippingRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;

    public List<Shipping> getAll() {
        return shippingRepository.findAll();
    }

    public void save(Shipping shipping){
        shippingRepository.save(shipping);
    }

    public Shipping getIdShipping(Long idShipping){
        return shippingRepository.findById(idShipping).get();
    }

    public void delete (Long idShipping){
        shippingRepository.deleteById(idShipping);
    }

}
