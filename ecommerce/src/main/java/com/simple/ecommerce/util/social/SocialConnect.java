package com.simple.ecommerce.util.social;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;


@Slf4j
@Component
public abstract class SocialConnect {
    
    public abstract String socialConnect() throws UnsupportedEncodingException;

    public abstract String socialGetToken(SocialConnectDto socialConnectDto);

    public abstract String socialUserByToken(SocialTokenDto socialTokenDto );
}
