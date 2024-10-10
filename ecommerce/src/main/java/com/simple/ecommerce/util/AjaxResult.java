package com.simple.ecommerce.util;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class AjaxResult<T> {
	private int status;
    private String message;
    private T data;
    private ErrorDetails error; 

    @Builder
    @Data
    public static class ErrorDetails {
        private String code;
        private String details;
    }
}
