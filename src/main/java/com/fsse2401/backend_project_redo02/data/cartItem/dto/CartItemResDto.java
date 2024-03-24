package com.fsse2401.backend_project_redo02.data.cartItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2401.backend_project_redo02.data.cartItem.domainOnject.CartItemResData;

import java.math.BigDecimal;

@JsonPropertyOrder({"pid", "name", "img_url", "price", "cart_quantity", "stock"})
public class CartItemResDto {
    private Integer pid;
    private String name;
    @JsonProperty("img_url")
    private String imgUrl;
    private BigDecimal price;
    @JsonProperty("cart_quantity")
    private Integer quantity;
    private Integer stock;

    public CartItemResDto(CartItemResData data) {
        this.pid = data.getProduct().getPid();
        this.name = data.getProduct().getName();
        this.imgUrl = data.getProduct().getImgUrl();
        this.price = data.getProduct().getPrice();
        this.quantity = data.getQuantity();
        this.stock = data.getProduct().getStock();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
