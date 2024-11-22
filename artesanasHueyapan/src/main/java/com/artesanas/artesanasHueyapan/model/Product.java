package com.artesanas.artesanasHueyapan.model;

import java.util.List;

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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 1000, message = "The content must be at most 1000")
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    @Column(name = "price")
    @JsonProperty("price")
    private double price;

    @NotNull(message = "Stock must not be null")
    @Min(value = 0, message = "Stock must be zero or positive")
    @Column(name = "stock")
    @JsonProperty("stock")
    private Long stock;

    // // @OneToOne
    // // @JoinColumn(name = "id_Category", referencedColumnName = "idCategory")
    // // @JsonBackReference
    // // private Category category;

    /*@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductCart> productCart;*/

    
}
