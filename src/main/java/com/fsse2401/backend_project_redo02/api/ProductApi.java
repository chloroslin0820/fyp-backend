package com.fsse2401.backend_project_redo02.api;

import com.fsse2401.backend_project_redo02.data.product.domainObject.CreateProductReqData;
import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.product.dto.request.CreateProductReqDto;
import com.fsse2401.backend_project_redo02.data.product.dto.response.GetAllProductResDto;
import com.fsse2401.backend_project_redo02.data.product.dto.response.ProductResDto;
import com.fsse2401.backend_project_redo02.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ProductResDto createProduct(@RequestBody CreateProductReqDto reqDto){
        return new ProductResDto(
                productService.createProduct(
                        new CreateProductReqData(reqDto)
                )
        );
    }
}
