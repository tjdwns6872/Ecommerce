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
    
    // 여러 플랫폼의 로그인 API를 사용하기 위한 커넥션 컴포넌트
    @Autowired
    private SocialConnectFactory socialConnectFactory;

    @Override
    public String login(Map<String, Object> params) {
        return null;
    }
    
    /**
     * 소셜로그인 메소드
     * @param platform - (String)사용자가 요청한 플랫폼 명
     * @return 토큰 값을 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
   */
    @Override
    public String login(String platform) {
        try {
            //socialConnectFactory를 통해 사용자가 요청한 플랫폼 커넥션을 생성
            SocialConnect socialConnect = socialConnectFactory.getSocialConnect(platform);
            // API 접근을 위한 URL 생성 후 리턴
            return socialConnect.socialConnect();
        } catch (UnsupportedEncodingException e) {
            log.error("Error during social login", e);
            // throw new SocialLoginException("Error during social login", e);
        }
        return null;
    }


    /**
     * 인증 후 리다이렉트된 매소드로 토큰 URL를 생성, 토큰 발급 후 유저 데이터를 처리하는 메소드 
     * @param socialConnectDto - (SocialConnectDto) 토큰 값을 요청할 때 사용할 파라미터 값
     * @param platform - (String) 사용자가 요청한 플랫폼 명
     * @return 토큰 값을 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
   */
    @Override
    public String socialCallback(SocialConnectDto socialConnectDto, String platform) {
        try {
            // API에서 토큰 발급을 위한 기본 grant_type값 삽입
            socialConnectDto.setGrantType("authorization_code");
            // 유저가 요청한 플랫폼 커넥션 생성
            SocialConnect socialConnect = socialConnectFactory.getSocialConnect(platform);
            // 발급 받은 인증코드를 기반으로 토큰 발급 URL 생성
            String url = socialConnect.socialGetTokenUrl(socialConnectDto);
            // 생성된 토큰 발급 URL로 토큰 발급
            SocialTokenDto socialTokenDto = socialConnect.socialGetToken(url);
            String userUrl = socialConnect.socialGetUrl();
            String data = socialConnect.socialGetUserData(socialTokenDto, "");

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
