package com.fsse2401.backend_project_redo02.service.impl;

import com.fsse2401.backend_project_redo02.data.cartItem.entity.CartItemEntity;
import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;
import com.fsse2401.backend_project_redo02.data.user.domainObject.FirebaseUserData;
import com.fsse2401.backend_project_redo02.data.user.entity.UserEntity;
import com.fsse2401.backend_project_redo02.exception.InvalidInputException;
import com.fsse2401.backend_project_redo02.exception.OutOfStockException;
import com.fsse2401.backend_project_redo02.repository.CartItemRepository;
import com.fsse2401.backend_project_redo02.service.CartItemService;
import com.fsse2401.backend_project_redo02.service.ProductService;
import com.fsse2401.backend_project_redo02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                               UserService userService,
                               ProductService productService){
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public boolean putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        checkInvalidInputExceptionByQuantityInteger(quantity);
        Optional<CartItemEntity> foundCartItemEntityOptional = cartItemRepository.findByUser_FirebaseUidAndProduct_Pid(firebaseUserData.getFirebaseUid(), pid);
        UserEntity foundUserEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);
        ProductEntity foundProductEntity = productService.getProductEntityByPid(pid);
        if(foundCartItemEntityOptional.isEmpty()){
            checkOutOfStockExceptionByStockIntegerAndQuantityInteger(foundProductEntity.getStock(), quantity);
            cartItemRepository.save(new CartItemEntity(quantity, foundUserEntity, foundProductEntity));
        }else{
            CartItemEntity foundCartItemEntity = foundCartItemEntityOptional.get();
            Integer addedQuantity = foundCartItemEntity.getQuantity() + quantity;
            checkOutOfStockExceptionByStockIntegerAndQuantityInteger(foundProductEntity.getStock(),
                    addedQuantity);
            foundCartItemEntity.setQuantity(addedQuantity);
            cartItemRepository.save(foundCartItemEntity);
        }
        return true;
    }

    //Exception
    public void checkInvalidInputExceptionByQuantityInteger(Integer quantity){
        if(quantity < 0){
            throw new InvalidInputException();
        }
    }

    public void checkOutOfStockExceptionByStockIntegerAndQuantityInteger(Integer stock, Integer quantity){
        if(stock - quantity < 0){
            throw new OutOfStockException();
        }
    }
}
