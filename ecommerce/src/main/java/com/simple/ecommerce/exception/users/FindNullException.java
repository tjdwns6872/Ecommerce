package com.simple.ecommerce.exception.users;

public class FindNullException extends NullPointerException{
    
    public FindNullException() {
        super("존재하지 않는 사용자입니다.");
    }

}
