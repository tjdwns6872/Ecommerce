package com.simple.ecommerce.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.simple.ecommerce.dto.SocialConnectDto;


@Slf4j
@Component
public abstract class SocialConnect {
    
    public abstract String socialConnect() throws UnsupportedEncodingException;

    public abstract String socialGetToken(SocialConnectDto socialConnectDto);
}
