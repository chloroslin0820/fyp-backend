package com.fsse2401.backend_project_redo02.data.product.domainObject;

import com.fsse2401.backend_project_redo02.data.product.dto.request.CreateProductReqDto;

import java.math.BigDecimal;

public class CreateProductReqData {
    private String name;
    private String description;
    private String imgUrl;
    private BigDecimal price;
    private Integer stock;

    public CreateProductReqData(CreateProductReqDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.imgUrl = dto.getImgUrl();
        this.price = dto.getPrice();
        this.stock = dto.getStock();
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
