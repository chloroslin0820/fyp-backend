package com.fsse2401.backend_project_redo02.service;

import com.fsse2401.backend_project_redo02.data.product.domainObject.CreateProductReqData;
import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductResData> getAllProduct();

    ProductResData getProductByPid(Integer pid);

    ProductResData createProduct(CreateProductReqData createProductReqData);

    boolean updateProductPriceByPid(Integer pid, Integer price);

    boolean discountProductPriceByPid(Integer pid, Integer discount);

    boolean updateProductStockByPid(Integer pid, Integer stock);

    boolean deductProductStockByPid(Integer pid, Integer stock);

    boolean deleteProductByPid(Integer pid);

    List<ProductResData> deleteOutOfStockProduct();

    //Repository
    ProductEntity getProductEntityByPid(Integer pid);
}
