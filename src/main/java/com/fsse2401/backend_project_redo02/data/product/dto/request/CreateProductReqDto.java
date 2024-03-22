package com.fsse2401.backend_project_redo02.data.product.dto.request;

import java.math.BigDecimal;

public class CreateProductReqDto {
    private String name;
    private String description;
    private String imgUrl;
    private BigDecimal price;
    private Integer stock;

    public CreateProductReqDto(String name, String description, String imgUrl, BigDecimal price, Integer stock) {
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
