package com.fsse2401.backend_project_redo02.data.cartItem.dto;

import com.fsse2401.backend_project_redo02.data.cartItem.domainOnject.StatusCartItemResData;
import com.fsse2401.backend_project_redo02.data.cartItem.status.CartItemStatus;

public class StatusCartItemResDto {
    CartItemStatus result;

    public StatusCartItemResDto(StatusCartItemResData data) {
        setResult(data.isResult());
    }

    public CartItemStatus getResult() {
        return result;
    }

    public void setResult(CartItemStatus result) {
        this.result = result;
    }

    public CartItemStatus setResult(boolean isResult){
        if(isResult){
            this.result = CartItemStatus.SUCCESS;
        }else{
            this.result = CartItemStatus.FAILED;
        }
        return null;
    }
}
