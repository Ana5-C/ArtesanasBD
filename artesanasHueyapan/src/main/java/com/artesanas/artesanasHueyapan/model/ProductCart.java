package com.artesanas.artesanasHueyapan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "productCart")
@IdClass(ProductCartPK.class)
public class ProductCart {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCart")
    @JsonBackReference
    private Cart cart;

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct")
    //@JsonBackReference
    private Product product;

    @Column(name = "cantidadProductos")
    private Long quantityProducts;

}
