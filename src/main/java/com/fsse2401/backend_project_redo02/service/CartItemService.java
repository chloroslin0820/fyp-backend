package com.fsse2401.backend_project_redo02.service;

import com.fsse2401.backend_project_redo02.data.cartItem.domainOnject.CartItemResData;
import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;

import java.util.List;

public interface CartItemService {
    boolean putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemResData> getUserCart(FirebaseUserData firebaseUserData);

    CartItemResData updateUserCartItemQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    boolean removeUserCartItem(FirebaseUserData firebaseUserData, Integer pid);

    //Repository
    CartItemEntity getCartItemEntityByUserFirebaseUidAndProductPid(String firebaseUid, Integer pid);
}
