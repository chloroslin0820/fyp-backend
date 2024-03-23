package com.fsse2401.backend_project_redo02.repository;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    Optional<CartItemEntity> findByUser_FirebaseUidAndProduct_Pid(String firebaseUid, Integer pid);
}
