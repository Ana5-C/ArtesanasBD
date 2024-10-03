package com.artesanas.artesanasHueyapan.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;
    private Integer total;
    private String date;
    private String paymentMethod;

    public Payment(Integer total, String date, String paymentMethod) {
        this.total = total;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    public Payment() {

    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "payment [idPayment=" + idPayment + ", total=" + total + ", date=" + date + ", paymentMethod="
                + paymentMethod + "]";
    }

}
