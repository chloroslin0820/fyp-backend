package com.fsse2401.backend_project_redo02.data.user.entity;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.transaction.entity.TransactionEntity;
import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    @Column(nullable = false)
    private String firebaseUid;
    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "user")
    private List<CartItemEntity> cartItemBuying;
    @OneToMany(mappedBy = "user")
    private List<TransactionEntity> transactionJoining;

    public UserEntity(String firebaseUid, String email) {
        this.firebaseUid = firebaseUid;
        this.email = email;
    }

    public UserEntity(FirebaseUserData firebaseUserData) {
        this.uid = this.getUid();
        this.firebaseUid = firebaseUserData.getFirebaseUid();
        this.email = firebaseUserData.getEmail();
    }

    public UserEntity() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CartItemEntity> getCartItemBuying() {
        return cartItemBuying;
    }

    public void setCartItemBuying(List<CartItemEntity> cartItemBuying) {
        this.cartItemBuying = cartItemBuying;
    }

    public List<TransactionEntity> getTransactionJoining() {
        return transactionJoining;
    }

    public void setTransactionJoining(List<TransactionEntity> transactionJoining) {
        this.transactionJoining = transactionJoining;
    }
}
