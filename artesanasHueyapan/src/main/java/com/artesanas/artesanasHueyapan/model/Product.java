package com.artesanas.artesanasHueyapan.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    @JsonProperty("idProduct")
    private Long idProduct;

    @Column(name = "name", nullable = false, length = 100)
    @JsonProperty("name")
    private String name;

    @Column(name = "description", length = 255)
    @JsonProperty("description")
    private String description;

    @OneToOne // One-to-One relationship with Category
    @JoinColumn(name = "idCategory", nullable = false) // Foreign key
    @JsonBackReference // Prevents recursion issues
    private Category category;

    @Column(name = "price", nullable = false)
    @JsonProperty("price")
    private double price;

    @Column(name = "stock", nullable = false)
    @JsonProperty("stock")
    private Long stock;

    @Column(name = "size", length = 20)
    @JsonProperty("size")
    private String size;

    // Default constructor
    public Product() {
    }

    // Constructor with parameters
    public Product(String name, String description, Category category, double price, Long stock, String size) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.size = size;
    }

    // Getters and Setters
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // toString method without relationships to avoid recursion issues
    @Override
    public String toString() {
        return "Product [idProduct=" + idProduct + ", name=" + name + ", description=" + description +
               ", price=" + price + ", stock=" + stock + ", size=" + size + "]";
    }

    // equals and hashCode implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check for reference equality
        if (o == null || getClass() != o.getClass()) return false; // Check for null and class equality
        Product product = (Product) o; // Cast to Product
        return Objects.equals(idProduct, product.idProduct); // Check for equality based on idProduct
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduct); // Generate hash code based on idProduct
    }
}
