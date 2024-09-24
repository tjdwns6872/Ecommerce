package com.simple.ecommerce.service.user;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;

@Service
public interface UserService {
   String login(String type);

   // String login(String type);

   String socialCallback(SocialConnectDto socialConnectDto) throws JsonMappingException, JsonProcessingException;

   String socialToken(SocialTokenDto token);
}
