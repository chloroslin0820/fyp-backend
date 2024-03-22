package com.fsse2401.backend_project_redo02.service.impl;

import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;
import com.fsse2401.backend_project_redo02.repository.ProductRepository;
import com.fsse2401.backend_project_redo02.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResData> getAllProduct(){
        List<ProductResData> productResDataList = new ArrayList<>();
        for(ProductEntity entity : productRepository.findAll()){
            productResDataList.add(new ProductResData(entity));
        }
        return productResDataList;
    }
}
