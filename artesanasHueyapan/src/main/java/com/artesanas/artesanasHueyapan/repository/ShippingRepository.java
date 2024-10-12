package com.artesanas.artesanasHueyapan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artesanas.artesanasHueyapan.model.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository <Shipping, Long> {

}
