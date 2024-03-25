package com.fsse2401.backend_project_redo02.service.impl;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;
import com.fsse2401.backend_project_redo02.data.transaction.domainObject.TransactionResData;
import com.fsse2401.backend_project_redo02.data.transaction.entity.TransactionEntity;
import com.fsse2401.backend_project_redo02.data.transaction.status.TransactionStatus;
import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;
import com.fsse2401.backend_project_redo02.data.user.entity.UserEntity;
import com.fsse2401.backend_project_redo02.exception.AlreadyProcessingOrSucessException;
import com.fsse2401.backend_project_redo02.exception.OutOfStockException;
import com.fsse2401.backend_project_redo02.repository.TransactionRepository;
import com.fsse2401.backend_project_redo02.service.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CartItemService cartItemService;
    private final ProductService productService;
    private final TransactionProductService transactionProductService;
    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  UserService userService,
                                  CartItemService cartItemService,
                                  ProductService productService,
                                  TransactionProductService transactionProductService){
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.productService = productService;
        this.transactionProductService = transactionProductService;
    }

    @Override
    public TransactionResData prepareTransaction(FirebaseUserData firebaseUserData){
        UserEntity foundUserEntity =
                userService.getUserEntityByFirebaseUserData(
                        firebaseUserData
                );
        List<CartItemEntity> foundCartItemEntityList =
                cartItemService.getCartItemEntityListByUserId(foundUserEntity.getUid());
        List<TransactionProductEntity> items = new ArrayList<>();
        TransactionEntity setTransactionEntity = new TransactionEntity(foundUserEntity, items);
        for(CartItemEntity cartItemEntity : foundCartItemEntityList){
            BigDecimal sum = BigDecimal.ZERO;
            setTransactionEntity.setTotal(sum);
            transactionRepository.save(setTransactionEntity);

            TransactionProductEntity setTransactionProductEntity =
                    transactionProductService.getTransactionProductEntityByCartItemEntityAndTransactionEntity(
                            cartItemEntity, setTransactionEntity
                    );
            items.add(setTransactionProductEntity);
            setTransactionEntity.setItems(items);

            for(CartItemEntity entity : foundCartItemEntityList){
                sum = sum.add(entity.getProduct().getPrice().multiply(BigDecimal.valueOf(entity.getQuantity())));
            }
            setTransactionEntity.setTotal(sum);

            transactionRepository.save(setTransactionEntity);
        }
        return new TransactionResData(setTransactionEntity);
    }

    @Override
    public TransactionResData getTransaction(FirebaseUserData firebaseUserData, Integer tid){
        return new TransactionResData(getTransactionEntityByTid(tid));
    }

    @Override
    public boolean payTransaction(FirebaseUserData firebaseUserData, Integer tid){
        TransactionEntity foundTransactionEntity = getTransactionEntityByTid(tid);
        checkAlreadyProcessingOrSuccessException(foundTransactionEntity);
        UserEntity foundUser = foundTransactionEntity.getUser();
        for(CartItemEntity cartItemEntity : cartItemService.getCartItemEntityListByUserId(foundUser.getUid())){
            Integer deductedStock = cartItemEntity.getProduct().getStock() - cartItemEntity.getQuantity();
            checkOutOfStockByCountedStockException(deductedStock);
            ProductEntity foundProductEntity = cartItemEntity.getProduct();
            foundTransactionEntity.setStatus(TransactionStatus.PROCESSING);
            productService.saveProductEntityByDeductedStockAndFoundProductEntity(
                    deductedStock, foundProductEntity);
        }
        return true;
    }

    @Override
    @Transactional
    public TransactionResData finishTransaction(FirebaseUserData firebaseUserData, Integer tid){
        TransactionEntity foundTransactionEntity = getTransactionEntityByStatusAndTid(tid);
        foundTransactionEntity.setStatus(TransactionStatus.SUCCESS);
        cartItemService.deleteCartItemEntityByUid(
                foundTransactionEntity.getUser().getUid());
        return new TransactionResData(foundTransactionEntity);
    }

    //Repository
    public TransactionEntity getTransactionEntityByTid(Integer tid){
        return transactionRepository.findById(tid).get();
    }

    public TransactionEntity getTransactionEntityByStatusAndTid(Integer tid){
        return transactionRepository.findByStatusAndTid(TransactionStatus.PROCESSING, tid).get();
    }

    //Exception
    public void checkAlreadyProcessingOrSuccessException(
            TransactionEntity foundTransactionEntity){
        if(foundTransactionEntity.getStatus().equals(TransactionStatus.PROCESSING)
                ||foundTransactionEntity.getStatus().equals(TransactionStatus.SUCCESS)){
            throw new AlreadyProcessingOrSucessException();
        }
    }

    public void checkOutOfStockByCountedStockException(Integer countedStock){
        if(countedStock < 0){
            throw new OutOfStockException();
        }
    }
}
