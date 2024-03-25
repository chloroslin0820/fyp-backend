package com.fsse2401.backend_project_redo02.service;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.transaction.entity.TransactionEntity;
import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;

import java.util.List;

public interface TransactionProductService {
    boolean deductProductStock(Integer tid, List<CartItemEntity> foundCartItemEntityList);

    //Repository
    TransactionProductEntity getTransactionProductEntityByCartItemEntityAndTransactionEntity(
            CartItemEntity foundCartItemEntity, TransactionEntity transactionEntity);
}
