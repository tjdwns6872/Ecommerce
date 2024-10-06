package com.simple.ecommerce.util.social.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.security.SecureRandom;
import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;
import com.simple.ecommerce.dto.social.SocialUserDto;
import com.simple.ecommerce.dto.social.kakao.KakaoProfileDto;
import com.simple.ecommerce.dto.social.kakao.KakaoReturnDto;
import com.simple.ecommerce.dto.social.kakao.KakaoUserDto;
import com.simple.ecommerce.util.social.AbstractSocialConnect;
import com.simple.ecommerce.util.social.SocialConnect;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//플랫폼 다양화를 위해 만든 커넥션 추상 클래스 상속
@Component
@Slf4j
public class KakaoConnect extends AbstractSocialConnect{
    
    //properties에 존재하는 kakao.api.client.id값 CLIENT_ID에 삽입
    @Value("${kakao.api.client.id}")
    private String CLIENT_ID;

    //properties에 존재하는 kakao.api.url값 KAKAO_AUTH_URL에 삽입
    @Value("${kakao.api.url}")
    private String KAKAO_AUTH_URL;

    //properties에 존재하는 kakao.api.callback.url값 REDIRECT_URL에 삽입
    @Value("${kakao.api.callback.url}")
    private String REDIRECT_URL;

    //properties에 존재하는 kakao.api.user.data.url값 USER_DATA_URL에 삽입
    @Value("${kakao.api.user.data.url}")
    private String USER_DATA_URL;

    /**
     * 카카오 로그인 인가코드를 위한 URL생성 메소드.
     * @return 인가코드를 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
     */
    @Override
    public String socialConnect() throws UnsupportedEncodingException {
        
        // 생성할 URL를 보관할 변수 생성 Buffer로 선언한 이유는 파라미터값을 삽입하기 위해 String로 선언할 경우 효율 저하 발생
        StringBuffer url = new StringBuffer();
        // properties에서 가져온 KAKAO_AUTH_URL상수에 카카오에서 요청하는 API타입을 합쳐서 url변수에 입력
        url.append(KAKAO_AUTH_URL+"authorize?");
        // properties에서 가져온 CLIENT_ID상수를 파라미터 값으로 추가
        url.append("client_id="+CLIENT_ID);
        // 카카오에서 정해준 response_type를 파라미터에 추가
        url.append("&response_type=code");
        // 인증 후 인가코드를 발급 받을 callback URL를 URLEncoder로 인코딩 후 파라미터에 추가
        url.append("&redirect_uri="+URLEncoder.encode(REDIRECT_URL, "UTF-8"));

        // 생성한 URL 리턴
        return url.toString();
    }

    /**
     * 토큰 값을 요청할 URL 생성 메소드.
     * @param socialConnectDto - (SocialConnectDto)토큰 값 요청할 URL을 생성할 때 쓸 파라미터 데이터
     * @return 토큰 값을 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
     */
    @Override
    public String socialGetTokenUrl(SocialConnectDto socialConnectDto) {
        // 토큰을 발급을 위한 URL를 생성하기 위한 변수 생성
        StringBuffer uriComponents = new StringBuffer();
        
        // properties에서 가져온 KAKAO_AUTH_URL상수에 카카오에서 요청하는 API타입을 합쳐서 url변수에 입력
        uriComponents.append(KAKAO_AUTH_URL+"token?");
        // 카카오에서 요청하는 grant_type 파라미터 값 추가
        uriComponents.append("grant_type="+socialConnectDto.getGrantType());
        // properties에서 가져온 CLIENT_ID상수를 파라미터 값으로 추가
        uriComponents.append("&client_id="+CLIENT_ID);
        // 카카오에서 로그인 인증 후 발급 받은 인가코드를 파라미터 값으로 추가
        uriComponents.append("&code="+socialConnectDto.getCode());
        try {
            // 인가코드를 발급 받았던 리다이렉트 URL를 URLEncoder로 인코딩 후 파라미터에 추가
            uriComponents.append("&redirect_uri="+URLEncoder.encode(REDIRECT_URL, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 토큰 발급URL 생성
        return uriComponents.toString();
    }

    @Override
    public String socialGetUrl() {
        return USER_DATA_URL;
    }

    @Override
    public SocialUserDto UserDataToDto(String data) throws JsonMappingException, JsonProcessingException {
        // 문자열 데이터를 변환하기 위한 클래스
        ObjectMapper mapper = new ObjectMapper();

        // 문자열을 KakaoReturnDto형태로 변환
        KakaoReturnDto returnDto = mapper.readValue(data, KakaoReturnDto.class);

        // returnDto에 있는 유저 데이터(kakao_account) userDto로 변환(이메일이 포함된 데이터)
        KakaoUserDto userDto = returnDto.getKakaoAccount();

        // userDto에 있는 유저 데이터(profile) proDto로 변환(이름이 포함된 데이터)
        KakaoProfileDto proDto = userDto.getProfile();

        // 필요한 데이터만 추출해서 리턴
        SocialUserDto dto = new SocialUserDto();

        // 유저 이메일 추출
        dto.setEmail(userDto.getEmail());
        // 유저 이름 추출
        dto.setName(proDto.getNickname());
        
        // 추출한 데이터 리턴
        return dto;
    }

}
