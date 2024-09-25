package com.simple.ecommerce.component.social;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simple.ecommerce.util.social.SocialConnect;

@Component
public class SocialConnectFactory {
    
    @Autowired
    private Map<String, SocialConnect> socialConnectMap;

    public SocialConnect getSocialConnect(String platform) {
        return socialConnectMap.get(platform + "Connect");
    }
}