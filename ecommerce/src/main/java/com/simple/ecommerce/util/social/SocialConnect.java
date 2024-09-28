package com.simple.ecommerce.util.social;

import java.io.UnsupportedEncodingException;

import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;

public interface SocialConnect {
    
    public abstract String socialConnect() throws UnsupportedEncodingException;

    public abstract String socialGetTokenUrl(SocialConnectDto socialConnectDto);

    public abstract String socialUserByToken(SocialTokenDto socialTokenDto );

    public String socialGetToken(String uriComponents);
}