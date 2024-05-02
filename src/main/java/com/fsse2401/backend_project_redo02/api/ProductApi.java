package com.fsse2401.backend_project_redo02.api;

import com.fsse2401.backend_project_redo02.config.EnvConfig;
import com.fsse2401.backend_project_redo02.data.product.domainObject.CreateProductReqData;
import com.fsse2401.backend_project_redo02.data.product.domainObject.ProductResData;
import com.fsse2401.backend_project_redo02.data.product.domainObject.StatusProductResData;
import com.fsse2401.backend_project_redo02.data.product.dto.request.CreateProductReqDto;
import com.fsse2401.backend_project_redo02.data.product.dto.response.DeleteOutOfStockProductResDto;
import com.fsse2401.backend_project_redo02.data.product.dto.response.GetAllProductResDto;
import com.fsse2401.backend_project_redo02.data.product.dto.response.ProductResDto;
import com.fsse2401.backend_project_redo02.data.product.dto.response.StatusProductResDto;
import com.fsse2401.backend_project_redo02.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public/product")
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL, EnvConfig.PROD_S3_BASE_URL})
//@CrossOrigin("*")
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

    @GetMapping("/{pid}")
    public ProductResDto getProductByPid(@PathVariable Integer pid){
        return new ProductResDto(
                productService.getProductByPid(pid)
        );
    }

    @PostMapping("/create")
    public ProductResDto createProduct(@RequestBody CreateProductReqDto reqDto){
        return new ProductResDto(
                productService.createProduct(
                        new CreateProductReqData(reqDto)
                )
        );
    }

    @PatchMapping("/{pid}/price/{price}")
    public StatusProductResDto updateProductPriceByPid(@PathVariable Integer pid, @PathVariable Integer price){
        return new StatusProductResDto(
                new StatusProductResData(
                        productService.updateProductPriceByPid(pid, price)
                )
        );
    }

    @PatchMapping("/{pid}/discount/{discount}")
    public StatusProductResDto discountProductPriceByPid(@PathVariable Integer pid, @PathVariable Integer discount){
        return new StatusProductResDto(
                new StatusProductResData(
                        productService.discountProductPriceByPid(pid, discount)
                )
        );
    }

    @PatchMapping("/{pid}/stock/{stock}")
    public StatusProductResDto updateProductStockByPid(@PathVariable Integer pid, @PathVariable Integer stock){
        return new StatusProductResDto(
                new StatusProductResData(
                        productService.updateProductStockByPid(pid, stock)
                )
        );
    }

    @PatchMapping("/{pid}/deduct_stock/{stock}")
    public StatusProductResDto deductProductStockByPid(@PathVariable Integer pid, @PathVariable Integer stock){
        return new StatusProductResDto(
                new StatusProductResData(
                        productService.deductProductStockByPid(pid, stock)
                )
        );
    }

    @PatchMapping("/{pid}/delete")
    public StatusProductResDto deleteProductByPid(@PathVariable Integer pid){
        return new StatusProductResDto(
                new StatusProductResData(
                        productService.deleteProductByPid(pid)
                )
        );
    }

    @PatchMapping("/out_of_stock/delete")
    public List<DeleteOutOfStockProductResDto> deleteOutOfStockProductByPid(){
        List<DeleteOutOfStockProductResDto> deleteOutOfStockProductResDtoList = new ArrayList<>();
        for(ProductResData productResData : productService.deleteOutOfStockProduct()){
            deleteOutOfStockProductResDtoList.add(new DeleteOutOfStockProductResDto(productResData));
        }
        return deleteOutOfStockProductResDtoList;
    }
}
