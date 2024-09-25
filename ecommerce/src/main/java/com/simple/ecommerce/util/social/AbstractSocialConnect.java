package com.simple.ecommerce.util.social;

import java.io.UnsupportedEncodingException;

import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;

public abstract class AbstractSocialConnect implements SocialConnect {
    // 공통 로직 구현
    public abstract String socialConnect() throws UnsupportedEncodingException;

    public abstract String socialGetToken(SocialConnectDto socialConnectDto);

    public abstract String socialUserByToken(SocialTokenDto socialTokenDto );
}