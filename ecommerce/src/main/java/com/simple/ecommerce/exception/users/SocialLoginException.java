package com.simple.ecommerce.exception.users;

public class SocialLoginException extends NullPointerException{
    
    public SocialLoginException(){
        super("가입되지 않은 이메일입니다.");
    }
}
