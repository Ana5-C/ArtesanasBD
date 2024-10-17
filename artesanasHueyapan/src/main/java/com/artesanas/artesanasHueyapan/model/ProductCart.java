package com.artesanas.artesanasHueyapan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//a class that represents a data entity
@Entity
@Table(name = "productCart")
public class ProductCart {
    /*Unique identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Attributes
    private Long idProductCart;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 100, message = "The content must be at most 100")
    @Column(name = "quantityProducts")
    @JsonProperty("quantityProducts")
    private Long quantityProducts;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 100, message = "The content must be at most 100")
    @Column(name = "productName")
    @JsonProperty("productName")
    private String productName;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 10000, message = "The content must be at most 10000")
    @Column(name = "unitPrice")
    @JsonProperty("unitPrice")
    private double unitPrice;

    /*
     * Many-to-one relationship with the Cart entity.
     * A product in the cart belongs to a cart.
     */
    @ManyToOne
    @JoinColumn(name = "id_cart", referencedColumnName = "idCart")
    // Used to avoid recursion when serializing the ProductCart entity in JSON
    // format
    @JsonBackReference
    private Cart cart;

    // @ManyToOne
    // @JoinColumn(name = "id_producto", referencedColumnName = "idProducto")
    // private Product product;

    /* Constructor with parameters */
    public ProductCart(String productName) {
        this.productName = productName;
    }

    public ProductCart() {
    }

    // Methods Getters y Setters
    public Long getIdProductCart() {
        return idProductCart;
    }

    public void setIdProductCart(Long idProductCart) {
        this.idProductCart = idProductCart;
    }

    public Long getQuantityProducts() {
        return quantityProducts;
    }

    public void setQuantityProducts(Long quantityProducts) {
        this.quantityProducts = quantityProducts;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // Returns a string representation of the object
    @Override
    public String toString() {
        return "ProductCart [idProductCart=" + idProductCart + ", quantityProducts=" + quantityProducts
                + ", productName=" + productName + ", unitPrice=" + unitPrice + ", cart=" + cart + "]";
    }
}
