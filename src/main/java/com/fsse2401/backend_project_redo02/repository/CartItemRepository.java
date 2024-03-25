package com.fsse2401.backend_project_redo02.repository;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    Optional<CartItemEntity> findByUser_FirebaseUidAndProduct_Pid(String firebaseUid, Integer pid);
    List<CartItemEntity> findAllByUser(UserEntity foundUserEntity);
    List<CartItemEntity>findAllByUserUid(Integer Uid);
    Optional<CartItemEntity> deleteByUserUid(Integer uid);
}
