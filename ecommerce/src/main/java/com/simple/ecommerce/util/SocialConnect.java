package com.simple.ecommerce.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class SocialConnect {
    
    public abstract String socialConnect(String type) throws UnsupportedEncodingException;

    public abstract String socialGetToken(String socialType, String grant_type, String state, String code);
}
