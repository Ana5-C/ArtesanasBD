package com.artesanas.artesanasHueyapan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artesanas.artesanasHueyapan.model.ProductCart;
import com.artesanas.artesanasHueyapan.repository.ProductCartRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductCartService {
    @Autowired
    private ProductCartRepository productCartRepository;

    public List<ProductCart> getAll(){
        return productCartRepository.findAll();
    }

    public void save(ProductCart productCart){
        productCartRepository.save(productCart);
    }

    public ProductCart getByProductCart(Long idProductCart){
        return productCartRepository.findById(idProductCart).get();
    }
    
    public void delete(Long idProductCart){
        productCartRepository.deleteById(idProductCart);
    }
}
