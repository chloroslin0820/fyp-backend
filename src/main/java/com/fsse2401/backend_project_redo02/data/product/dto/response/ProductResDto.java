package com.fsse2401.backend_project_redo02.data.product.dto.response;

import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;

import java.math.BigDecimal;

public class ProductResDto {
    private Integer pid;
    private String name;
    private String description;
    private String imgUrl;
    private BigDecimal price;
    private Integer stock;

    public ProductResDto(ProductResData data) {
        this.pid = data.getPid();
        this.name = data.getName();
        this.description = data.getDescription();
        this.imgUrl = data.getImgUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
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
