package com.artesanas.artesanasHueyapan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//a class representing a data entity
@Entity
@Table(name = "customers")
public class Customer {

    //unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //attributes
    private Long idCustomer;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    @Column(name = "lastName")
    @JsonProperty("lastName")
    private String lastName;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 50, message = "The content must be at most 50")
    @Column(name = "email")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 8, message = "The content must be at most 8")
    @Column(name = "userName")
    @JsonProperty("userName")
    private String userName;

    @NotBlank(message = "The content not be blank")
    @Size(min = 1, max = 8, message = "The content must be at most 8")
    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    /*
     * One-to-one relationship with the Customer entity.
     * A cart belongs to a customer.
     */
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // used to avoid recursion when serializing
    @JsonBackReference
    private Cart cart;

    // Methods Getters y Setters
    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // returns a string representation of the object
    @Override
    public String toString() {
        return "Customer [idCustomer=" + idCustomer + ", name=" + name + ", lastName=" + lastName + ", email=" + email
                + ", userName=" + userName + ", password=" + password + ", cart=" + cart + "]";
    }

}
