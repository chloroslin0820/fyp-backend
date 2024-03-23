package com.fsse2401.backend_project_redo02.service;

import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;
import com.fsse2401.backend_project_redo02.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserEntityByFirebaseUserData(FirebaseUserData firebaseUserData);
}
