package com.simple.ecommerce.util.social.naver;

import com.liferay.portal.kernel.security.SecureRandom;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;
import com.simple.ecommerce.util.social.AbstractSocialConnect;
import com.simple.ecommerce.util.social.SocialConnect;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NaverConnect extends AbstractSocialConnect{
    
    @Value("${naver.api.client.id}")
    private String CLIENT_ID;

    @Value("${naver.api.url}")
    private String NAVER_AUTH_URL;

    @Value("${naver.api.callback.url}")
    private String REDIRECT_URL;

    @Value("${naver.api.client.secret}")
    private String CLIENT_SECRET;

    @Value("${naver.api.user.data.url}")
    private String USER_DATA_URL;

    @Override
    public String socialConnect() throws UnsupportedEncodingException {
        
        StringBuffer url = new StringBuffer();
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString(32);

        url.append(NAVER_AUTH_URL+"authorize?");
        url.append("client_id="+CLIENT_ID);
        url.append("&response_type=code");
        url.append("&redirect_url="+URLEncoder.encode(REDIRECT_URL, "UTF-8"));
        url.append("&state="+URLEncoder.encode(state, "UTF-8"));

        return url.toString();
    }

    @Override
    public String socialGetTokenUrl(SocialConnectDto socialConnectDto) {
        log.info("\n\nstate=[{}]\n\ncode=[{}]", socialConnectDto.getState(), socialConnectDto.getCode());

        StringBuffer uriComponents = new StringBuffer();
        
        uriComponents.append(NAVER_AUTH_URL+"token?");
        uriComponents.append("grant_type="+socialConnectDto.getGrantType());
        uriComponents.append("&client_id="+CLIENT_ID);
        uriComponents.append("&client_secret="+CLIENT_SECRET);
        uriComponents.append("&code="+socialConnectDto.getCode());
        uriComponents.append("&state="+socialConnectDto.getState());

        return uriComponents.toString();
    }

    @Override
    public String socialGetUrl() {
        return USER_DATA_URL;
    }

    
}
