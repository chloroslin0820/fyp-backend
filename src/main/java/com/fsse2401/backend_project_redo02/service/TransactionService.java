package com.fsse2401.backend_project_redo02.service;

import com.fsse2401.backend_project_redo02.data.transaction.domainObject.TransactionResData;
import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;
import jakarta.transaction.Transactional;

public interface TransactionService {
    TransactionResData prepareTransaction(FirebaseUserData firebaseUserData);

    TransactionResData getTransaction(FirebaseUserData firebaseUserData, Integer tid);

    boolean payTransaction(FirebaseUserData firebaseUserData, Integer tid);

    @Transactional
    TransactionResData finishTransaction(FirebaseUserData firebaseUserData, Integer tid);
}
