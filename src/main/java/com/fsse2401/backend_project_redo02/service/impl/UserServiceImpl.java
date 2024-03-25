package com.fsse2401.backend_project_redo02.service.impl;

import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;
import com.fsse2401.backend_project_redo02.data.user.entity.UserEntity;
import com.fsse2401.backend_project_redo02.repository.UserRepository;
import com.fsse2401.backend_project_redo02.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserEntityOrElseSaveUserEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
        return userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid()).orElseGet(()->userRepository.save(new UserEntity(firebaseUserData)));
    }

    //Repository
    @Override
    public UserEntity getUserEntityByFirebaseUserData(FirebaseUserData firebaseUserData){
        return userRepository.findByFirebaseUid(firebaseUserData.getFirebaseUid()).orElseThrow(EntityNotFoundException::new);
    }

}
