package com.simple.ecommerce.exception.users;

public class JoinException extends RuntimeException{
    
    public JoinException(){
        super("회원가입 실패");
    }
}
