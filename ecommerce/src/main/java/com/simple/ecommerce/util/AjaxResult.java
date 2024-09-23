package com.simple.ecommerce.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data @NoArgsConstructor
public class AjaxResult {
	
	private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";
    
    private static final Integer SUCCESS_CODE = 200;
    private static final Integer ERROR_CODE = 400;
    private static final Integer FAIL_CODE = 500;
    private static final Integer BADREQUEST_CODE = 404;
	
	private String status;
	private Integer code;
	private Object data;
	private String message;
	
	public void createSuccess(Object data) {
        setResult(SUCCESS_CODE, SUCCESS_STATUS, data, null);
    }

	public void createSuccessWithNoContent() {
        setResult(SUCCESS_CODE, SUCCESS_STATUS, null, null);
    }
	
    // Hibernate Validator에 의해 유효하지 않은 데이터로 인해 API 호출이 거부될때 반환
    public void createFail(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError error : allErrors) {
            if (error instanceof FieldError) {
                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
            } else {
                errors.put( error.getObjectName(), error.getDefaultMessage());
            }
        }
        setResult(FAIL_CODE, FAIL_STATUS, errors, null);
    }
    
    public void createFail(Exception e) {
        Map<String, String> errors = new HashMap<>();
        
        setResult(FAIL_CODE, FAIL_STATUS, e, "Unknown Error");
    }
    
	// 예외 발생으로 API 호출 실패시 반환
    public void createError(String message) {
        setResult(ERROR_CODE, ERROR_STATUS, null, message);
    }

    private void setResult(Integer code, String status, Object data, String message) {
        this.code = code;
    	this.status = status;
        this.data = data;
        this.message = message;
    }
    
    public Map<String, Object> getResult() {
    	Map<String, Object> result = new HashMap<>();
        result.put("code", this.code);
    	result.put("status", this.status);
    	result.put("data", this.data);
    	result.put("message", this.message);
		return result;
    }

}
