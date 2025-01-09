package com.simple.ecommerce.controller.products;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.products.InsertDto;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.service.products.ProductsService;
import com.simple.ecommerce.util.AjaxResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequestMapping("/ecommerce/api/product")
@RestController
public class ProductsRestController {
    
    private final ProductsService productsService;

    public ProductsRestController(ProductsService productsService){
        this.productsService = productsService;
    }

    @PostMapping("/insert")
    public ResponseEntity<AjaxResult<Integer>> productInsert(@RequestBody InsertDto insertDto) {
        Integer result = productsService.dataInsert(insertDto);
        AjaxResult<Integer> response = AjaxResult.<Integer>builder()
            .status(HttpStatus.OK.value())
            .message("상품 등록 완료")
            .data(result)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AjaxResult<Integer>> productUpdate(@RequestBody InsertDto updateDto) {
        Integer result = productsService.dataUpdate(updateDto);
        AjaxResult<Integer> response = AjaxResult.<Integer>builder()
            .status(HttpStatus.OK.value())
            .message("상품 등록 완료")
            .data(result)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<AjaxResult<ProductsEntity>> productDetail(@PathVariable Integer id){
        ProductsEntity result = productsService.dataDetailSelect(id);
        AjaxResult<ProductsEntity> response = AjaxResult.<ProductsEntity>builder()
            .status(HttpStatus.OK.value())
            .message("상품 조회")
            .data(result)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
}
