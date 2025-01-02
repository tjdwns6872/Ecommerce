package com.simple.ecommerce.controller.products;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.products.InsertDto;
import com.simple.ecommerce.util.AjaxResult;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RequestMapping("/ecommerce/api/product")
@RestController
@Slf4j
public class ProductsRestController {
    
    @PutMapping("/insert")
    public ResponseEntity<AjaxResult<String>> productInsert(@RequestBody InsertDto insertDto) {
        AjaxResult<String> response = AjaxResult.<String>builder()
            .status(HttpStatus.OK.value())
            .message("상품 등록 완료")
            .data("")
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
}
