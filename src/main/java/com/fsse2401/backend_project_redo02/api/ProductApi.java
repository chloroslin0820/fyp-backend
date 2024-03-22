package com.fsse2401.backend_project_redo02.api;

import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.product.dto.GetAllProductResDto;
import com.fsse2401.backend_project_redo02.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
public class ProductApi {
    private final ProductService productService;
    @Autowired
    public ProductApi(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<GetAllProductResDto> getAllProduct(){
        List<GetAllProductResDto> getAllProductResDtoList = new ArrayList<>();
        for(ProductResData productResData : productService.getAllProduct()){
            getAllProductResDtoList.add(new GetAllProductResDto(productResData));
        }
        return getAllProductResDtoList;
    }
}
