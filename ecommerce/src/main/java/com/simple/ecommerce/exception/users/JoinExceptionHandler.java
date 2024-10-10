package com.simple.ecommerce.exception.users;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simple.ecommerce.util.AjaxResult;

// 핸들러를 활용해 오류 발생 시 해당 Exception을 찾아 실행
@RestControllerAdvice
public class JoinExceptionHandler {
    
    // 회원가입 오류 핸들러 JoinException 발생 시 실행
    @ExceptionHandler(JoinException.class)
    public ResponseEntity<AjaxResult<Void>> joinException(JoinException ex) {
        //AjaxResult 데이터 포맷에 맞춰 데이터 삽입
        AjaxResult<Void> response = AjaxResult.<Void>builder()
            // HttpStatus BAD_REQUEST 상태코드(400)
            .status(HttpStatus.BAD_REQUEST.value())
            // 노출될 메세지
            .message("회원가입에 실패했습니다.")
            // 에러에 대한 상세 메시지 (수정 예정)
            .error(AjaxResult.ErrorDetails.builder()
                .code("FIELD CREATE ACCOUNT")
                .details(ex.getMessage())
                .build())
            .build();
        
        return ResponseEntity.badRequest().body(response);
    }
}

