package com.simple.ecommerce.exception.products;

public class ProductsException extends RuntimeException{

    private String detailMessage;
    private String message;

    public ProductsException(String message, String detailMessage){
        super(message);
        this.detailMessage = detailMessage;
    }

    public ProductsException(String message){
        super(message);
    }

    public String getDetailMessage(){
        return this.detailMessage;
    }
    
}
