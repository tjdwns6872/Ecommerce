package com.simple.ecommerce.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.SocialConnectDto;
import com.simple.ecommerce.service.UserService;
import com.simple.ecommerce.util.NaverConnect;

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
    public String socialCallback(SocialConnectDto socialConnectDto) {
        String token = "";
        socialConnectDto.setGrantType("authorization_code");
        token = naverConnect.socialGetToken(socialConnectDto);
        String userData = socialToken(token);

        return token;
    }

    @Override
    public String socialToken(String token) {
        log.info("\n\ntoken==>{}\n\n", token);
        return new String();
    }
    
}
