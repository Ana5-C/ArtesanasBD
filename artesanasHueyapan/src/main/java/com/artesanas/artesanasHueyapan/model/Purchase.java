package com.artesanas.artesanasHueyapan.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase") 
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpurchase")
    @JsonProperty("idpurchase")
    private Long idPurchase; // Primary ID

    @Column(name = "date", nullable = false)
    @JsonProperty("date")
    private LocalDate date;

    @Column(name = "total", nullable = false)
    @JsonProperty("total")
    private double total;

    @Column(name = "amount", nullable = false)
    @JsonProperty("amount")
    private int amount;

    @Column(name = "phone", length = 20)
    @JsonProperty("phone")
    private String phone;

    // Default constructor
    public Purchase() {
    }

    // Constructor with fields (excluding idPurchase, which is auto-generated)
    public Purchase(LocalDate date, double total, int amount, String phone) {
        this.date = date;
        this.total = total;
        this.amount = amount;
        this.phone = phone;
    }

    // Getters and setters
    public Long getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(Long idPurchase) {
        this.idPurchase = idPurchase;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // toString method
    @Override
    public String toString() {
        return "Purchase [idPurchase=" + idPurchase + ", date=" + date + 
               ", total=" + total + ", amount=" + amount + 
               ", phone=" + phone + "]";
    }

    // equals and hashCode implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(idPurchase, purchase.idPurchase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPurchase);
    }
}