package com.fsse2401.backend_project_redo02.data.transactionProduct.entity;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.transaction.entity.TransactionEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transaction_product")
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;
    @ManyToOne
    @JoinColumn(name = "transaction_tid", nullable = false)
    private TransactionEntity transaction;
    @Column(nullable = false)
    private Integer pid;
    @Column(nullable = false)
    private String name;
    private String description;
    private String imgUrl;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private Integer quantity;

    public TransactionProductEntity() {
    }

    public TransactionProductEntity(CartItemEntity foundCartItemEntity,
                                    TransactionEntity transactionEntity) {
        this.transaction = transactionEntity;
        this.pid = foundCartItemEntity.getProduct().getPid();
        this.name = foundCartItemEntity.getProduct().getName();
        this.description = foundCartItemEntity.getProduct().getDescription();
        this.imgUrl = foundCartItemEntity.getProduct().getImgUrl();
        this.price = foundCartItemEntity.getProduct().getPrice();
        this.stock = foundCartItemEntity.getProduct().getStock();
        this.quantity = foundCartItemEntity.getQuantity();
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
