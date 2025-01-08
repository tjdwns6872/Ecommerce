package com.simple.ecommerce.exception.products;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simple.ecommerce.util.AjaxResult;

// 핸들러를 활용해 오류 발생 시 해당 Exception을 찾아 실행
@RestControllerAdvice
public class ProductsExceptionHandler {
  
        
        // 회원가입 오류 핸들러 JoinException 발생 시 실행
        @ExceptionHandler(ProductsException.class)
        public ResponseEntity<AjaxResult<Void>> productsException(ProductsException ex) {
            //AjaxResult 데이터 포맷에 맞춰 데이터 삽입
            AjaxResult<Void> response = AjaxResult.<Void>builder()
                .status(HttpStatus.SC_BAD_REQUEST)
                .message(ex.getMessage())
                .error(AjaxResult.ErrorDetails.builder()
                    .code(Integer.toString(HttpStatus.SC_BAD_REQUEST))
                    .details(ex.getDetailMessage())
                    .build())
                .build();
            
            return ResponseEntity.badRequest().body(response);
        }
    
}
