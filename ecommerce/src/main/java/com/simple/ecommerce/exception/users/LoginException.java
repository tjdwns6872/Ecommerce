package com.simple.ecommerce.exception.users;

public class LoginException extends RuntimeException{
    
    public LoginException() {
        super("로그인 실패");
    }
}
