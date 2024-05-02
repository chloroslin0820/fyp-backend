package com.fsse2401.backend_project_redo02.data.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;

import java.math.BigDecimal;

@JsonPropertyOrder({"pid", "name", "description", "img_url", "price", "has_stock"})
public class GetAllProductResDto {
    private Integer pid;
    private String name;
    private String description;
    @JsonProperty("img_url")
    private String imgUrl;
    private BigDecimal price;
    @JsonProperty("has_stock")
    private boolean hasStock;

    public GetAllProductResDto(ProductResData data) {
        this.pid = data.getPid();
        this.name = data.getName();
        this.description = data.getDescription();
        this.imgUrl = data.getImgUrl();
        this.price = data.getPrice();
        this.hasStock = data.getStock() > 0;
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

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
