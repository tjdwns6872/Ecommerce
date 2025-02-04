package com.simple.ecommerce.controller.products;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.ecommerce.dto.products.ProductDeleteDto;
import com.simple.ecommerce.dto.products.ProductInsertDto;
import com.simple.ecommerce.dto.products.ProductSelectResponse;
import com.simple.ecommerce.dto.products.SelectDto;
import com.simple.ecommerce.dto.products.SelectRequestDto;
import com.simple.ecommerce.entity.products.ProductsEntity;
import com.simple.ecommerce.service.products.ProductsService;
import com.simple.ecommerce.util.AjaxResult;
import com.simple.ecommerce.util.JwtUtil;
import com.simple.ecommerce.util.PagingUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RequestMapping("/ecommerce/api/product")
@RestController
public class ProductsRestController {
    
    private final ProductsService productsService;
    private final JwtUtil jwtUtil;

    public ProductsRestController(ProductsService productsService
            ,JwtUtil jwtUtil){
        this.productsService = productsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/insert")
    public ResponseEntity<AjaxResult<Integer>> productInsert(HttpServletRequest request
                                                            , @RequestBody ProductInsertDto insertDto) {
        String token = jwtUtil.resolveToken(request);
        insertDto.setUserToken(token);
        Integer result = productsService.dataInsert(insertDto);
        AjaxResult<Integer> response = AjaxResult.<Integer>builder()
            .status(HttpStatus.OK.value())
            .message("상품 등록 완료")
            .data(result)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<AjaxResult<Integer>> productUpdate(HttpServletRequest request
                                                            , @RequestBody ProductInsertDto updateDto) {
        String token = jwtUtil.resolveToken(request);
        updateDto.setUserToken(token);
        Integer result = productsService.dataUpdate(updateDto);
        AjaxResult<Integer> response = AjaxResult.<Integer>builder()
            .status(HttpStatus.OK.value())
            .message("상품 등록 완료")
            .data(result)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AjaxResult<Integer>> productDelete(HttpServletRequest request
                                                            , @PathVariable Integer id){
        ProductDeleteDto dto = ProductDeleteDto.builder().productId(id).userToken(jwtUtil.resolveToken(request)).build();
        Integer result = productsService.dataDelete(dto);
        AjaxResult<Integer> response = AjaxResult.<Integer>builder()
            .status(HttpStatus.OK.value())
            .message("상품 삭제")
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

    @GetMapping("/list")
    public ResponseEntity<AjaxResult<ProductSelectResponse>> productList(SelectRequestDto dto){
        SelectDto sDto = new SelectDto();
        Pageable pageable = PagingUtil.getPaging(dto.getPage(), dto.getSize(), dto.getSort(), dto.getSortWay());
        sDto.setPageable(pageable);
        sDto.setRequestDto(dto);
        ProductSelectResponse data = productsService.dataListSelect(sDto);
        AjaxResult<ProductSelectResponse> response = AjaxResult.<ProductSelectResponse>builder()
            .status(HttpStatus.OK.value())
            .message("상품 조회")
            .data(data)
            .build();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
}
