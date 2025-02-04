package com.simple.ecommerce.serviceimpl.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.service.jwt.JwtService;
import com.simple.ecommerce.util.JwtUtil;

@Service
public class JwtServiceImpl implements JwtService{

    private JwtUtil jwtUtil;

    public JwtServiceImpl(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Integer tokenToUserId(String token) {
        Authentication userData = jwtUtil.getAuthentication(token);
        return Integer.valueOf(userData.getName());
    }
    
}
