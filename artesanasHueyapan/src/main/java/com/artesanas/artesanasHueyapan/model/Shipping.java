package com.artesanas.artesanasHueyapan.model;

import jakarta.persistence.*;;

@Entity
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idShipping;
    private String parcel;
    private String trackingNumber;

    @ManyToOne
    @JoinColumn(name = "id_address")
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
