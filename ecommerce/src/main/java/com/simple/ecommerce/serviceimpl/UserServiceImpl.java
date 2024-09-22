package com.simple.ecommerce.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.commons.SocialConnect;
import com.simple.ecommerce.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    
    @Autowired
    private SocialConnect socialConnect;

    @Override
    public String login() {
        String url = "";
        try {
            url = socialConnect.socialConnect("naver");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public String socialCallback(Map<String, Object> params) {
        String token = socialConnect.socialGetToken("naver", "authorization_code", params.get("state").toString(), params.get("code").toString());
        return token;
    }
    
}
