package com.simple.ecommerce.exception.sms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simple.ecommerce.util.AjaxResult;

@RestControllerAdvice
public class codeErrorExceptionHandler {
    
    @ExceptionHandler(codeErrorException.class)
    public ResponseEntity<AjaxResult<Void>> codeError(codeErrorException e){
        //AjaxResult 데이터 포맷에 맞춰 데이터 삽입
        AjaxResult<Void> response = AjaxResult.<Void>builder()
            // HttpStatus BAD_REQUEST 상태코드(400)
            .status(HttpStatus.BAD_REQUEST.value())
            // 노출될 메세지
            .message("인증에 실패했습니다.")
            // 에러에 대한 상세 메시지 (수정 예정)
            .error(AjaxResult.ErrorDetails.builder()
                .code("AUTHENTICATION FAILED")
                .details(e.getMessage())
                .build())
            .build();
        return ResponseEntity.badRequest().body(response);
    }
}
