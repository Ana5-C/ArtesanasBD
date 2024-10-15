package com.artesanas.artesanasHueyapan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artesanas.artesanasHueyapan.model.Purchase;
import com.artesanas.artesanasHueyapan.repository.PurchaseRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchaseService {
    
    @Autowired
    private PurchaseRepository repository;

    // Get all purchases
    public List<Purchase> getAll() {
        return repository.findAll();
    }

    // Save a new purchase
    public void save(Purchase purchase) {
        repository.save(purchase);
    }

    // Get a purchase by ID
    public Optional<Purchase> getById(Long idPurchase) {
        return repository.findById(idPurchase);
    }

    // Delete a purchase by ID
    public void delete(Long idPurchase) {
        repository.deleteById(idPurchase);
    }

    // Get purchases by phone number
    public List<Purchase> getByPhone(String phone) {
        return repository.findByPhone(phone);
    }
}
