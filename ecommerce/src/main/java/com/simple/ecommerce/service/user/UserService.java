package com.simple.ecommerce.service.user;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;

@Service
public interface UserService {
   String login(String platform);

   String login(Map<String, Object> params); //USER DTO 변경예정

   String socialCallback(SocialConnectDto socialConnectDto, String platform) throws JsonMappingException, JsonProcessingException;

   String socialToken(SocialTokenDto token);
}
