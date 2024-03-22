package com.fsse2401.backend_project_redo02.data.product.domainObject;

import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;

import java.math.BigDecimal;

public class ProductResData {
    private Integer pid;
    private String name;
    private String description;
    private String imgUrl;
    private BigDecimal price;
    private Integer stock;

    public ProductResData(ProductEntity entity) {
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.imgUrl = entity.getImgUrl();
        this.price = entity.getPrice();
        this.stock = entity.getStock();
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
