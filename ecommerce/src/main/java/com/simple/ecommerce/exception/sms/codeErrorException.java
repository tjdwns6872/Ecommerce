package com.simple.ecommerce.exception.sms;

public class codeErrorException extends NullPointerException{
    
    public codeErrorException() {
        super("인증번호가 일치하지 않습니다.");
    }

}
