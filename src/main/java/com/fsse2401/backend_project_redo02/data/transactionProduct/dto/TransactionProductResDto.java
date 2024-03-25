package com.fsse2401.backend_project_redo02.data.transactionProduct.dto;

import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.transactionProduct.domainObject.TransactionProductResData;

import java.math.BigDecimal;

public class TransactionProductResDto {
    private Integer tpid;
    private ProductResData product;
    private Integer quantity;
    private BigDecimal subtotal;

    public TransactionProductResDto(TransactionProductResData data) {
        this.tpid = data.getTpid();
        this.product = data.getProduct();
        this.quantity = data.getQuantity();
        this.subtotal = data.getProduct().getPrice().multiply(BigDecimal.valueOf(data.getQuantity()));
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public ProductResData getProduct() {
        return product;
    }

    public void setProduct(ProductResData product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
