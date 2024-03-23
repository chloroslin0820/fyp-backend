package com.fsse2401.backend_project_redo02.api;

import com.fsse2401.backend_project_redo02.data.cartItem.domainOnject.StatusCartItemResData;
import com.fsse2401.backend_project_redo02.data.cartItem.dto.StatusCartItemResDto;
import com.fsse2401.backend_project_redo02.service.CartItemService;
import com.fsse2401.backend_project_redo02.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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
}
