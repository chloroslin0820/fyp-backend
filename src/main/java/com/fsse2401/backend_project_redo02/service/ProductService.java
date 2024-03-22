package com.fsse2401.backend_project_redo02.service;

import com.fsse2401.backend_project_redo02.data.product.domainObject.CreateProductReqData;
import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;

import java.util.List;

public interface ProductService {
    List<ProductResData> getAllProduct();

    ProductResData createProduct(CreateProductReqData createProductReqData);
}
