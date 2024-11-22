package com.artesanas.artesanasHueyapan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artesanas.artesanasHueyapan.model.ProductCart;
import com.artesanas.artesanasHueyapan.model.ProductCartPK;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, ProductCartPK>{
}
