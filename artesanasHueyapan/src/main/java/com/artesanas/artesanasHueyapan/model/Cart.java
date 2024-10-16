package com.artesanas.artesanasHueyapan.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//a class representing a data entity
@Entity
@Table(name = "cart")
public class Cart {

    // unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // attributes
    private Long idCart;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 10, message = "The content must be at most 10")
    @Column(name = "dateCreated")
    @JsonProperty("dateCreated")
    private String dateCreated;

    /*
     * One-to-many relationship with ProductCart.
     * A cart can have many products
     */
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "idCustomer")
    // se utiliza para evitar la recursión al serializar
    @JsonManagedReference
    private Customer customer;

    /*
     * Relacion de uno a muchos con ProductCart.
     * Un carrito puuede tener muchos productos
     */
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductCart> productsCart;

    /* constructor with parameters */
    public Cart(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Cart() {
    }

    /* Methods Getters y Setters */
    public Long getIdCart() {
        return idCart;
    }

    public void setIdCart(Long idCart) {
        this.idCart = idCart;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductCart> getProductsCart() {
        return productsCart;
    }

    public void setProductsCart(List<ProductCart> productsCart) {
        this.productsCart = productsCart;
    }

    // returns a string representation of the object
    @Override
    public String toString() {
        return "Cart [idCart=" + idCart + ", dateCreated=" + dateCreated + ", customer=" + customer + ", productsCart="
                + productsCart + "]";
    }
}
