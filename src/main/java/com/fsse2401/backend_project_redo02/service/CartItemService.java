package com.fsse2401.backend_project_redo02.service;

import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;

public interface CartItemService {
    boolean putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);
}
