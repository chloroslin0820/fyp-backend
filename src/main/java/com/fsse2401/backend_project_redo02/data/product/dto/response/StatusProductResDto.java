package com.fsse2401.backend_project_redo02.data.product.dto.response;

import com.fsse2401.backend_project_redo02.data.product.domainObject.StatusProductResData;
import com.fsse2401.backend_project_redo02.data.product.status.ProductStatus;

public class StatusProductResDto {
    ProductStatus result;

    public StatusProductResDto(StatusProductResData data) {
        setResult(data.isResult());
    }

    public ProductStatus getResult() {
        return result;
    }

    public void setResult(ProductStatus result) {
        this.result = result;
    }

    public ProductStatus setResult(boolean isResult){
        if(isResult){
            this.result = ProductStatus.SUCCESS;
        }else{
            this.result = ProductStatus.FAILED;
        }
        return null;
    }
}
