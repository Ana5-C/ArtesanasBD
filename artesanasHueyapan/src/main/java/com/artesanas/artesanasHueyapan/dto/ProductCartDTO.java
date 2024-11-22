package com.artesanas.artesanasHueyapan.dto;

public class ProductCartDTO {
    private Long cartId;
    private Long productId;
    private Long quantityProducts;

    
    public Long getCartId() {
        return cartId;
    }
    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Long getQuantityProducts() {
        return quantityProducts;
    }
    public void setQuantityProducts(Long quantityProducts) {
        this.quantityProducts = quantityProducts;
    }

}
