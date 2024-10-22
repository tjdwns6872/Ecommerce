package com.simple.ecommerce.exception.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simple.ecommerce.util.AjaxResult;

@RestControllerAdvice
public class FindExceptionHandler {
    
    @ExceptionHandler(FindNullException.class)
    public ResponseEntity<AjaxResult<Void>> FindNullException(FindNullException ex) {
        AjaxResult<Void> response = AjaxResult.<Void>builder()
            .status(HttpStatus.BAD_REQUEST.value())
            .message("해당 정보의 회원을 찾을 수 없습니다.")
            .error(AjaxResult.ErrorDetails.builder()
                .code("The member for that information could not be found.")
                .details(ex.getMessage())
                .build())
            .build();
        
        return ResponseEntity.badRequest().body(response);
    }
}
