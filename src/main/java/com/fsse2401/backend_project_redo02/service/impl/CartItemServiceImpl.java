package com.fsse2401.backend_project_redo02.service.impl;

import com.fsse2401.backend_project_redo02.data.cartItem.domainOnject.CartItemResData;
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
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        Optional<CartItemEntity> foundCartItemEntityOptional =
                cartItemRepository.findByUser_FirebaseUidAndProduct_Pid(
                        firebaseUserData.getFirebaseUid(), pid);
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

    @Override
    public List<CartItemResData> getUserCart(FirebaseUserData firebaseUserData){
        UserEntity foundUserEntity =
                userService.getUserEntityOrElseSaveUserEntityByFirebaseUserData(
                        firebaseUserData);
        List<CartItemResData> cartItemResDataList = new ArrayList<>();
        for(CartItemEntity entity : foundUserEntity.getCartItemBuying()){
            cartItemResDataList.add(new CartItemResData(entity));
        }
        return cartItemResDataList;
    }

    @Override
    public CartItemResData updateUserCartItemQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        checkInvalidInputExceptionByQuantityInteger(quantity);
        CartItemEntity foundCartItemEntity =
                getCartItemEntityByUserFirebaseUidAndProductPid(
                        firebaseUserData.getFirebaseUid(), pid);
        checkOutOfStockExceptionByStockIntegerAndQuantityInteger(
                foundCartItemEntity.getProduct().getStock(), quantity);
        foundCartItemEntity.setQuantity(quantity);
        cartItemRepository.save(foundCartItemEntity);
        return new CartItemResData(foundCartItemEntity);
    }

    @Override
    public boolean removeUserCartItem(FirebaseUserData firebaseUserData, Integer pid){
        cartItemRepository.delete(
                getCartItemEntityByUserFirebaseUidAndProductPid(
                        firebaseUserData.getFirebaseUid(), pid));
        return true;
    }

    //Repository
    @Override
    public CartItemEntity getCartItemEntityByUserFirebaseUidAndProductPid(String firebaseUid, Integer pid){
        return cartItemRepository.findByUser_FirebaseUidAndProduct_Pid(firebaseUid, pid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<CartItemEntity> getCartItemEntityListByUserEntity(UserEntity foundUserEntity){
        return cartItemRepository.findAllByUser(foundUserEntity);
    }

    @Override
    public List<CartItemEntity> getCartItemEntityListByUserId(Integer uid){
        return cartItemRepository.findAllByUserUid(uid);
    }

    @Override
    @Transactional
    public Boolean deleteCartItemEntityByUid(Integer uid){
        cartItemRepository.deleteByUserUid(uid);
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
