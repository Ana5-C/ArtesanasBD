package com.artesanas.artesanasHueyapan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artesanas.artesanasHueyapan.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
