package com.simple.ecommerce.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.SocialConnectDto;

@Service
public interface UserService {
   String login(String type);

   // String login(String type);

   String socialCallback(SocialConnectDto socialConnectDto);

   String socialToken(String token);
}
