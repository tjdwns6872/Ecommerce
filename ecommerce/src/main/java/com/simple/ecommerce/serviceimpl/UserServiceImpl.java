package com.simple.ecommerce.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.service.UserService;
import com.simple.ecommerce.util.NaverConnect;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    
    @Autowired
    private NaverConnect naverConnect;

    @Override
    public String login(String type) {
        String url = "";
        try {
            if(type.equals("naver")){
                url = naverConnect.socialConnect(type);
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
    public String socialCallback(Map<String, Object> params) {
        String token = "";
        token = naverConnect.socialGetToken("naver", "authorization_code", params.get("state").toString(), params.get("code").toString());
        log.info("\n\n{}\n", token);
        return token;
    }
    
}
