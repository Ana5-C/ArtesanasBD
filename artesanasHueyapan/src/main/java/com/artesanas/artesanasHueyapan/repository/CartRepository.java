package com.artesanas.artesanasHueyapan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artesanas.artesanasHueyapan.model.Cart;

//@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value= "SELECT * FROM cart WHERE fecha = :dateCreated", nativeQuery = true)
    List<Cart> getCartByDateNative(@Param("dateCreated") String dateCreated);

    @Query(value = "SELECT c FROM Cart c WHERE c.dateCreated = :dateCreated")
    List<Cart> getCartByDateJPQL(@Param("dateCreated") String dateCreated);
}
