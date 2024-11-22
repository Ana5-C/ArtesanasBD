package com.artesanas.artesanasHueyapan.dto;
import java.sql.Date;
import java.util.List;


public class CartRequestDTO {
    private Long id;
    private Date  dateCreated;
    private Long customerId;
    private List<ProductCartDTO> productCartDTOs;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public List<ProductCartDTO> getProductCartDTOs() {
        return productCartDTOs;
    }
    public void setProductCartDTOs(List<ProductCartDTO> productCartDTOs) {
        this.productCartDTOs = productCartDTOs;
    }
}
