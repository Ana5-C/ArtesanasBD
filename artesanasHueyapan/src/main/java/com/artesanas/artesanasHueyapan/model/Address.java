package com.artesanas.artesanasHueyapan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //atributos
    private Long idAddress;
    private String city;
    private Integer zipCode;
    private String municipality;
    private String cologne;
    private String street;
    private Integer number;
    
    public Long getIdAddress() {
        return idAddress;
    }
    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Integer getZipCode() {
        return zipCode;
    }
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
    public String getMunicipality() {
        return municipality;
    }
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
    public String getCologne() {
        return cologne;
    }
    public void setCologne(String cologne) {
        this.cologne = cologne;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    
    @Override
    public String toString() {
        return "Address [idAddress=" + idAddress + ", city=" + city + ", zipCode=" + zipCode + ", municipality="
                + municipality + ", cologne=" + cologne + ", street=" + street + ", number=" + number + "]";
    }
    
    
    
}
