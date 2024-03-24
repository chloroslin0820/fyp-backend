package com.fsse2401.backend_project_redo02.api;

import com.fsse2401.backend_project_redo02.data.cartItem.domainOnject.CartItemResData;
import com.fsse2401.backend_project_redo02.data.cartItem.domainOnject.StatusCartItemResData;
import com.fsse2401.backend_project_redo02.data.cartItem.dto.CartItemResDto;
import com.fsse2401.backend_project_redo02.data.cartItem.dto.StatusCartItemResDto;
import com.fsse2401.backend_project_redo02.service.CartItemService;
import com.fsse2401.backend_project_redo02.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartItemApi {
    private final CartItemService cartItemService;
    @Autowired
    public CartItemApi(CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }

    @PutMapping("/{pid}/{quantity}")
    public StatusCartItemResDto putCartItem(JwtAuthenticationToken token,
                                               @PathVariable Integer pid,
                                               @PathVariable Integer quantity){
        return new StatusCartItemResDto(
                new StatusCartItemResData(
                        cartItemService.putCartItem(
                                JwtUtil.getFirebaseUserData(token), pid, quantity
                        )
                )
        );
    }

    @GetMapping
    public List<CartItemResDto> getUserCart(JwtAuthenticationToken token){
        List<CartItemResDto> cartItemResDtoList = new ArrayList<>();
        for(CartItemResData cartItemResData : cartItemService.getUserCart(JwtUtil.getFirebaseUserData(token))){
            cartItemResDtoList.add(new CartItemResDto(cartItemResData));
        }
        return cartItemResDtoList;
    }

    @PatchMapping("/{pid}/{quantity}")
    public CartItemResDto updateUserCartItemQuantity(JwtAuthenticationToken token,
                                                 @PathVariable Integer pid,
                                                 @PathVariable Integer quantity){
        return new CartItemResDto(
                cartItemService.updateUserCartItemQuantity(
                        JwtUtil.getFirebaseUserData(token), pid, quantity
                )
        );
    }

    @DeleteMapping("/{pid}")
    public StatusCartItemResDto removeUserCartItem(JwtAuthenticationToken token,
                                                   @PathVariable Integer pid){
        return new StatusCartItemResDto(
                new StatusCartItemResData(
                        cartItemService.removeUserCartItem(
                                JwtUtil.getFirebaseUserData(token), pid
                        )
                )
        );
    }
}
