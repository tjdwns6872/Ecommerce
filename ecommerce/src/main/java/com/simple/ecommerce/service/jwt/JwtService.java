package com.simple.ecommerce.service.jwt;

public interface JwtService {
    
    public Integer tokenToUserId(String token);
}
