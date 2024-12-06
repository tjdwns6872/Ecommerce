package com.simple.ecommerce.exception.users;

public class FindBadRequestException extends RuntimeException{
    
    public FindBadRequestException() {
        super("잘못된 요청입니다.");
    }
}
