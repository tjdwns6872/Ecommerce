package com.simple.ecommerce.service.user;


import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.users.UsersLoginDto;
import com.simple.ecommerce.entity.users.UsersEntity;

@Service
public interface UsersLoginService {
   
   /**
     * 소셜로그인 인터페이스
     * @param platform - (String)사용자가 요청한 플랫폼 명
     * @return 토큰 값을 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
   */
   String login(String platform);

   /**
     * 일반회원 로그인 인터페이스
     * @param UsersLoginDto - UsersLoginDto
     * @return 토큰 값을 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
   */
   String login(UsersLoginDto dto) throws JsonProcessingException;

   /**
     * 소셜로그인시 넘어오는 인증코드 처리를 위한 인터페이스
     * @param socialConnectDto - (SocialConnectDto) 토큰 값을 요청할 때 사용할 파라미터 값
     * @param platform - (String) 사용자가 요청한 플랫폼 명
     * @return 토큰 값을 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
   */
   String socialCallback(SocialConnectDto socialConnectDto, String platform) throws JsonMappingException, JsonProcessingException;

   String socialTokenRefresh();
}
