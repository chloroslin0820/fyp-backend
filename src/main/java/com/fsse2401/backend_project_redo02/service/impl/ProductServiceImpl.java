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
    public ProductResData getProductByPid(Integer pid){
        return new ProductResData(getProductEntityByPid(pid));
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

    @Override
    public boolean updateProductPriceByPid(Integer pid, Integer price){
        checkInvalidInputExceptionByPriceBigDecimal(BigDecimal.valueOf(price));
        ProductEntity foundProductEntity = productRepository.findById(pid).get();
        foundProductEntity.setPrice(BigDecimal.valueOf(price));
        productRepository.save(foundProductEntity);
        return true;
    }

    @Override
    public boolean discountProductPriceByPid(Integer pid, Integer discount){
        checkInvalidInputExceptionByDiscountInteger(discount);
        ProductEntity foundProductEntity = productRepository.findById(pid).get();
        foundProductEntity.setPrice(
                (foundProductEntity.getPrice().multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100)))
        );
        productRepository.save(foundProductEntity);
        return true;
    }

    @Override
    public boolean updateProductStockByPid(Integer pid, Integer stock){
        checkInvalidInputExceptionByStockInteger(stock);
        ProductEntity foundProductEntity = productRepository.findById(pid).get();
        foundProductEntity.setStock(stock);
        productRepository.save(foundProductEntity);
        return true;
    }

    @Override
    public boolean deductProductStockByPid(Integer pid, Integer stock){
        checkInvalidInputExceptionByStockInteger(stock);
        ProductEntity foundProductEntity = productRepository.findById(pid).get();
        Integer modifiedStock = foundProductEntity.getStock() - stock;
        if(modifiedStock < 0){
            return false;
        }
        foundProductEntity.setStock(modifiedStock);
        productRepository.save(foundProductEntity);
        return true;
    }

    @Override
    public boolean deleteProductByPid(Integer pid){
        for(ProductEntity productEntity : productRepository.findAll()){
            if(productEntity.getPid().equals(pid)){
                productRepository.delete(productEntity);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ProductResData> deleteOutOfStockProduct(){
        List<ProductResData> productResDataList = new ArrayList<>();
        for(ProductEntity productEntity : productRepository.findAll()){
            if(productEntity.getStock() <= 0 ){
                productResDataList.add(new ProductResData(productEntity));
                productRepository.delete(productEntity);
            }
        }
        return productResDataList;
    }

    //Repository
    @Override
    public ProductEntity getProductEntityByPid(Integer pid){
        return productRepository.findById(pid).get();
    }

    //Exceptions
    public void checkDataMissingExceptionByCreateProductReqData(CreateProductReqData data){
        if(data.getName().isEmpty()
                || data.getPrice() == null
                || data.getStock() == null){
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

    public void checkInvalidInputExceptionByDiscountInteger(Integer discount){
        if(discount <= 0 || discount >= 100){
            throw new InvalidInputException();
        }
    }

}
