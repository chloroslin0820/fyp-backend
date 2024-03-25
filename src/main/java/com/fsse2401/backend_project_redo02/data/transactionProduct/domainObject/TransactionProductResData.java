package com.fsse2401.backend_project_redo02.data.transactionProduct.domainObject;

import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;
import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;

public class TransactionProductResData {
    private Integer tpid;
    private ProductResData product;
    private Integer quantity;

    public TransactionProductResData(TransactionProductEntity entity) {
        this.tpid = entity.getTpid();
        this.product = new ProductResData(new ProductEntity(entity));
        this.quantity = entity.getQuantity();
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

}
