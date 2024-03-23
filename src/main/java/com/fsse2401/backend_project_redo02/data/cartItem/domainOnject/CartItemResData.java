package com.fsse2401.backend_project_redo02.data.cartItem.domainOnject;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;
import com.fsse2401.backend_project_redo02.data.user.entity.UserEntity;

public class CartItemResData {
    private Integer cid;
    private Integer quantity;
    private UserEntity user;
    private ProductEntity product;

    public CartItemResData(CartItemEntity entity) {
        this.cid = entity.getCid();
        this.quantity = entity.getQuantity();
        this.user = entity.getUser();
        this.product = entity.getProduct();
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}


