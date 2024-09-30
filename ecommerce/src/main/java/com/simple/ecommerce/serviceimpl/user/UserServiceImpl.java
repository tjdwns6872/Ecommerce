package com.simple.ecommerce.serviceimpl.user;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.ecommerce.component.social.SocialConnectFactory;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;
import com.simple.ecommerce.service.user.UserService;
import com.simple.ecommerce.util.social.SocialConnect;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    
    @Autowired
    private SocialConnectFactory socialConnectFactory;

    @Override
    public String login(Map<String, Object> params) {
        return null;
    }
    
    @Override
    public String login(String platform) {
        try {
            SocialConnect socialConnect = socialConnectFactory.getSocialConnect(platform);
            return socialConnect.socialConnect();
        } catch (UnsupportedEncodingException e) {
            log.error("Error during social login", e);
            // throw new SocialLoginException("Error during social login", e);
        }
        return null;
    }


    @Override
    public String socialCallback(SocialConnectDto socialConnectDto, String platform) {
        try {
            socialConnectDto.setGrantType("authorization_code");
            SocialConnect socialConnect = socialConnectFactory.getSocialConnect(platform);
            String url = socialConnect.socialGetTokenUrl(socialConnectDto);
            SocialTokenDto socialTokenDto = socialConnect.socialGetToken(url);
            String userUrl = socialConnect.socialGetUrl();
            String data = socialConnect.socialGetUserData(socialTokenDto, userUrl);

            log.info("\n\ndata===>{}\n", data.toString());
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String socialToken(SocialTokenDto token) {
        return "";
    }

    /* 
     * @params
    */
    @Override
    public String socialTokenRefresh() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'socialTokenRefresh'");
    }
    
}
