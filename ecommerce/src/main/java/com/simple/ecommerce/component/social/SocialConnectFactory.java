package com.simple.ecommerce.component.social;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simple.ecommerce.util.social.SocialConnect;

@Component
public class SocialConnectFactory {
    
    @Autowired
    private Map<String, SocialConnect> socialConnectMap;

    /**
     * 전달 받은 String 값으로 해당 커넥션 리턴
     * ,로직 설명 : Map<String, SocialConnect> 타입으로 String+Connerct Key값으로 사용자가 요청항 플랫폼 커넥션 리턴
     * @param platform - (String)사용자가 요청한 플랫폼 명
     * @return SocialConnect - 사용자가 요청한 커넥션
     * @throws 어떤 상황에서 예외가 발생!
     */
    public SocialConnect getSocialConnect(String platform) {
        // Ex) platform(naver)+Connect 리턴
        return socialConnectMap.get(platform + "Connect");
    }
}