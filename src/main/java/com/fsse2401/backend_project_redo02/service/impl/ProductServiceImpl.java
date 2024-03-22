package com.fsse2401.backend_project_redo02.service.impl;

import com.fsse2401.backend_project_redo02.data.product.domainObject.CreateProductReqData;
import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.product.entity.ProductEntity;
import com.fsse2401.backend_project_redo02.exception.DataMissingException;
import com.fsse2401.backend_project_redo02.exception.InvalidInputException;
import com.fsse2401.backend_project_redo02.repository.ProductRepository;
import com.fsse2401.backend_project_redo02.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Override
    public ProductResData createProduct(CreateProductReqData createProductReqData){
        checkDataMissingExceptionByCreateProductReqData(createProductReqData);
        checkInvalidInputExceptionByPriceBigDecimal(createProductReqData.getPrice());
        checkInvalidInputExceptionByStockInteger(createProductReqData.getStock());
        checkInvalidInputExceptionByImgUrlString(createProductReqData.getImgUrl());

        ProductEntity entity = new ProductEntity(createProductReqData);
        productRepository.save(entity);
        return new ProductResData(entity);
    }

    public void checkDataMissingExceptionByCreateProductReqData(CreateProductReqData data){
        if(data.getName().isEmpty() ||
                data.getPrice() == null ||
                data.getStock() == null){
            throw new DataMissingException();
        }
    }

    public void checkInvalidInputExceptionByPriceBigDecimal(BigDecimal price){
        if(price.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidInputException();
        }
    }

    public void checkInvalidInputExceptionByStockInteger(Integer stock){
        if(stock <= 0){
            throw new InvalidInputException();
        }
    }

    public void checkInvalidInputExceptionByImgUrlString(String imgUrl){
        if(!imgUrl.contains("https://")){
            throw new InvalidInputException();
        }
    }
}
