package com.artesanas.artesanasHueyapan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String name;
    private String description;
    private Long categoryId;
    private double price;
    private Long stock;

    // Default constructor
    public Product() {
    }

    // Constructor with essential fields
    public Product(String name, String description, Long categoryId, double price, Long stock) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.stock = stock;
    }

    // Getter and Setter methods
    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Product [idProduct=" + idProduct + ", name=" + name + ", description=" + description +
                ", categoryId=" + categoryId + ", price=" + price + ", stock=" + stock + "]";
    }
}
