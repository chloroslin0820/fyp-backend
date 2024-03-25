package com.fsse2401.backend_project_redo02.service.impl;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;
import com.fsse2401.backend_project_redo02.data.transaction.entity.TransactionEntity;
import com.fsse2401.backend_project_redo02.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2401.backend_project_redo02.repository.TransactionProductRepository;
import com.fsse2401.backend_project_redo02.service.ProductService;
import com.fsse2401.backend_project_redo02.service.TransactionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {
    private final TransactionProductRepository transactionProductRepository;
    private final ProductService productService;
    @Autowired
    public TransactionProductServiceImpl(
            TransactionProductRepository transactionProductRepository,
            ProductService productService){
        this.transactionProductRepository = transactionProductRepository;
        this.productService = productService;
    }

    @Override
    public boolean deductProductStock(Integer tid, List<CartItemEntity> foundCartItemEntityList){
        for(CartItemEntity cartItemEntity : foundCartItemEntityList){
            Integer foundQuantity = cartItemEntity.getQuantity();
            ProductEntity deductedStockProductEntity = productService.saveDeductedStock(foundQuantity, cartItemEntity.getProduct());
            cartItemEntity.setProduct(deductedStockProductEntity);
            TransactionProductEntity foundTransactionProductEntity = transactionProductRepository.findByPid(cartItemEntity.getProduct().getPid()).get();
            transactionProductRepository.save(foundTransactionProductEntity);
        }
        return true;
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
