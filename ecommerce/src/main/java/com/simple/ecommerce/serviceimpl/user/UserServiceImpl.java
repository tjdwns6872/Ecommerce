package com.simple.ecommerce.serviceimpl.user;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;
import com.simple.ecommerce.service.user.UserService;
import com.simple.ecommerce.util.social.NaverConnect;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    
    @Autowired
    private NaverConnect naverConnect;

    @Override
    public String login(String platform) {
        String url = "";
        try {
            if(platform.equals("naver")){
                url = naverConnect.socialConnect();
            }else{
                url = null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("{}", e);
        }
        return url;
    }

    @Override
    public String socialCallback(SocialConnectDto socialConnectDto) throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        socialConnectDto.setGrantType("authorization_code");
        String tokenStr = naverConnect.socialGetToken(socialConnectDto);
        log.info("\n\n{}\n\n", tokenStr);
        SocialTokenDto token = mapper.readValue(tokenStr, SocialTokenDto.class);
        
        String userData = socialToken(token);

        return new String();
    }

    @Override
    public String socialToken(SocialTokenDto token) {
        String response = naverConnect.socialUserByToken(token);
        log.info("\n\nresponse==>{}\n\n", response);
        return response;
    }
    
}
