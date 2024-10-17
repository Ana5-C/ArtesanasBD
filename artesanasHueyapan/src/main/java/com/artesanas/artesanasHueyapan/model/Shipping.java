package com.artesanas.artesanasHueyapan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 100, message = "The content must be at most 500 characters, and has at least one character")
    @Column(name = "nombre")
    @JsonProperty("nombre")
    private Long idShipping;
    private String parcel;
    private String trackingNumber;

    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "nombre")
    @JsonBackReference
    private Address address;

    public Long getIdShipping() {
        return idShipping;
    }

    public void setIdShipping(Long idShipping) {
        this.idShipping = idShipping;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shipping [idShipping=" + idShipping + ", parcel=" + parcel + ", trackingNumber=" + trackingNumber
                + ", address=" + address + "]";
    }
    




    
}
