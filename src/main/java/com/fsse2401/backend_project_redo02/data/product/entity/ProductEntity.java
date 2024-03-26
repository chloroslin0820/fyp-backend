package com.fsse2401.backend_project_redo02.data.product.entity;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.product.domainObject.CreateProductReqData;
import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;
    @Column(nullable = false)
    private String name;
    private String description;
    private String imgUrl;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItemJoining;

    public ProductEntity(CreateProductReqData data) {
        this.name = data.getName();
        this.description = data.getDescription();
        this.imgUrl = data.getImgUrl();
        this.price = data.getPrice();
        this.stock = data.getStock();
    }

    public ProductEntity(TransactionProductEntity transactionProductEntity) {
        this.pid = transactionProductEntity.getPid();
        this.name = transactionProductEntity.getName();
        this.description = transactionProductEntity.getDescription();
        this.imgUrl = transactionProductEntity.getImgUrl();
        this.price = transactionProductEntity.getPrice();
        this.stock = transactionProductEntity.getStock();
    }

    public ProductEntity() {
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

    public List<CartItemEntity> getCartItemJoining() {
        return cartItemJoining;
    }

    public void setCartItemJoining(List<CartItemEntity> cartItemJoining) {
        this.cartItemJoining = cartItemJoining;
    }
}
