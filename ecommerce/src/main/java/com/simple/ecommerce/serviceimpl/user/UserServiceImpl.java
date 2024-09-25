package com.simple.ecommerce.serviceimpl.user;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.ecommerce.component.social.SocialConnectFactory;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;
import com.simple.ecommerce.service.user.UserService;
import com.simple.ecommerce.util.social.SocialConnect;
import com.simple.ecommerce.util.social.kakao.KakaoConnect;
import com.simple.ecommerce.util.social.naver.NaverConnect;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    
    @Autowired
    private NaverConnect naverConnect;

    @Autowired
    private KakaoConnect kakaoConnect;

    @Autowired
    private SocialConnectFactory socialConnectFactory;

    @Override
    public String login(String platform) {
        try {
            SocialConnect socialConnect = socialConnectFactory.getSocialConnect(platform);
            return socialConnect.socialConnect();
        } catch (UnsupportedEncodingException e) {
            log.error("Error during social login", e);
            // throw new SocialLoginException("Error during social login", e);
        }
        return new String();
    }

    @Override
    public String socialCallback(SocialConnectDto socialConnectDto, String platform) {
        try {
            socialConnectDto.setGrantType("authorization_code");
            ObjectMapper mapper = new ObjectMapper();
            SocialConnect socialConnect = socialConnectFactory.getSocialConnect(platform);
            String tokenStr = socialConnect.socialGetToken(socialConnectDto);
            SocialTokenDto token = mapper.readValue(tokenStr, SocialTokenDto.class);
            return socialConnect.socialUserByToken(token);
        } catch (JsonProcessingException e) {
            log.error("Error processing social callback", e);
            // throw new SocialLoginException("Error processing social callback", e);
        }
        return new String();
    }

    @Override
    public String socialToken(SocialTokenDto token) {
        String response = naverConnect.socialUserByToken(token);
        log.info("\n\nresponse==>{}\n\n", response);
        return response;
    }
    
}
