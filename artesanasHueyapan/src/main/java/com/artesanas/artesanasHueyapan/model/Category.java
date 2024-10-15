package com.artesanas.artesanasHueyapan.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    @JsonProperty("idCategoria")
    private Long idCategory;

    @Column(nullable = false, length = 100, name = "nombre")
    @JsonProperty("nombre")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Manage reference to avoid recursion issues
    private List<Product> products;

    // Default constructor
    public Category() {
    }

    // Constructor with fields
    public Category(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // toString method without relationships to avoid recursion issues
    @Override
    public String toString() {
        return "Category [idCategory=" + idCategory + ", name=" + name + "]";
    }

    // equals and hashCode implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(idCategory, category.idCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory);
    }
}
