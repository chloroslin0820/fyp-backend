package com.fsse2401.backend_project_redo02.service.impl;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.transaction.entity.TransactionEntity;
import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.backend_project_redo02.repository.TransactionProductRepository;
import com.fsse2401.backend_project_redo02.service.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    private final TransactionProductRepository transactionProductRepository;
    @Autowired
    public TransactionProductServiceImpl(
            TransactionProductRepository transactionProductRepository){
        this.transactionProductRepository = transactionProductRepository;
    }

    //Repository
    @Override
    public TransactionProductEntity getTransactionProductEntityByCartItemEntityAndTransactionEntity(
            CartItemEntity foundCartItemEntity, TransactionEntity transactionEntity){
        checkNullPointerExceptionByCartItemEntity(foundCartItemEntity);
        TransactionProductEntity storedTransactionProductEntity = new TransactionProductEntity(foundCartItemEntity, transactionEntity);
        transactionProductRepository.save(storedTransactionProductEntity);
        return storedTransactionProductEntity;
    }

    //Exception
    public void checkNullPointerExceptionByCartItemEntity(
            CartItemEntity foundCartItemEntity){
        if(foundCartItemEntity.getProduct().getPid() == null
                || foundCartItemEntity.getProduct().getName() == null
                || foundCartItemEntity.getProduct().getPrice() == null
                || foundCartItemEntity.getProduct().getStock() == null
                || foundCartItemEntity.getQuantity() == null){
            throw new NullPointerException();
        }
    }
}
