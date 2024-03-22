package com.fsse2401.backend_project_redo02.data.product.dto.response;

import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.product.status.ProductStatus;

import java.math.BigDecimal;

public class DeleteOutOfStockProductResDto {
    private Integer pid;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private ProductStatus status;

    public DeleteOutOfStockProductResDto(ProductResData data) {
        this.pid = data.getPid();
        this.name = data.getName();
        this.imgUrl = data.getImgUrl();
        this.price = data.getPrice();
        this.status = ProductStatus.REMOVED;
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

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
