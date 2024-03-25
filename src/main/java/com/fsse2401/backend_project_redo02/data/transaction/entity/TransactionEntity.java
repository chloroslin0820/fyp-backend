package com.fsse2401.backend_project_redo02.data.transaction.entity;

import com.fsse2401.backend_project_redo02.data.transaction.status.TransactionStatus;
import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.backend_project_redo02.data.user.entity.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;
    @OneToMany(mappedBy = "transaction")
    private List<TransactionProductEntity> items;
    @Column(nullable = false)
    private BigDecimal total;

    public TransactionEntity() {
    }

    public TransactionEntity(UserEntity foundUserEntity, List<TransactionProductEntity> items) {
        this.user = foundUserEntity;
        this.status = TransactionStatus.PREPARE;
        this.items = items;
        this.total = this.getTotal();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public List<TransactionProductEntity> getItems() {
        return items;
    }

    public void setItems(List<TransactionProductEntity> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
