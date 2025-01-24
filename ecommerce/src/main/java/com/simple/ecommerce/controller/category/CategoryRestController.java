package com.simple.ecommerce.controller.category;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.category.CategorySelectRequestDto;
import com.simple.ecommerce.dto.category.CategorySelectResponse;
import com.simple.ecommerce.entity.category.CategoryEntity;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.service.category.CategoryService;
import com.simple.ecommerce.util.AjaxResult;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/ecommerce/api/category")
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    
    @GetMapping("/list")
    public ResponseEntity<AjaxResult<CategorySelectResponse>> categoryList(CategorySelectRequestDto dto){
        CategorySelectResponse responseData = categoryService.dataListSelect(dto);
        AjaxResult<CategorySelectResponse> response = AjaxResult.<CategorySelectResponse>builder()
            .status(HttpStatus.OK.value())
            .message("카테고리 조회")
            .data(responseData)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<AjaxResult<CategoryEntity>> categoryDetail(@RequestParam Integer categoryId) {
        CategoryEntity result = categoryService.dataDetailSelect(categoryId);
        AjaxResult<CategoryEntity> response = AjaxResult.<CategoryEntity>builder()
            .status(HttpStatus.OK.value())
            .message("카테고리 상세 조회")
            .data(result)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
}
