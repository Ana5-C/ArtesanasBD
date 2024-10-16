package com.artesanas.artesanasHueyapan.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artesanas.artesanasHueyapan.model.Cart;
import com.artesanas.artesanasHueyapan.repository.CartRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAll(){
        return cartRepository.findAll();
    }

    public void save(Cart cart){
        cartRepository.save(cart);
    }

    public Cart getByIdCart(Long idCart){
        return cartRepository.findById(idCart).get();
    }

    public void delete(Long idCart){
        cartRepository.deleteById(idCart);
    }
}
