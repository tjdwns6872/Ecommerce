package com.simple.ecommerce.util.social;

import java.io.UnsupportedEncodingException;

import com.simple.ecommerce.dto.social.SocialConnectDto;
import com.simple.ecommerce.dto.social.SocialTokenDto;

public interface SocialConnect {
    
    /**
     * 소셜로그인 API 접근을 위한 URL 생성 인터페이스.
     * @return 인증코드를 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
     */
    public abstract String socialConnect() throws UnsupportedEncodingException;

    /**
     * 전달 받은 인증코드로 토큰을 발급 받는 URL 생성 인터페이스.
     * @param socialConnectDto - (SocialConnectDto)토큰 값 요청할 URL을 생성할 때 쓸 파라미터 데이터)
     * @return 토큰 값을 발급 받을 url값
     * @throws 어떤 상황에서 예외가 발생!
     */
    public abstract String socialGetTokenUrl(SocialConnectDto socialConnectDto);

    /**
     * 토큰 발급이후 DTO로 객체화해주는 인터페이스
     * @param uriComponents - (String)생성한 토큰 발급 URL
     * @return 토큰 값 리턴
     * @throws 어떤 상황에서 예외가 발생!
     */
    public SocialTokenDto socialGetToken(String uriComponents);

    /**
     * 유저 데이터 불러오는 인터페이스
     * @param uriComponents - 유저 데이터를 요청하는 URL
     * @param socialTokenDto - 유저 데이터 요청할 때 쓰이는 토큰
     * @return 토큰 값 리턴
     * @throws 어떤 상황에서 예외가 발생!
     */
    public String socialGetUserData(SocialTokenDto socialTokenDto, String uriComponents);

    /**
     * 유저 데이터 불러오는 URL을 생성하는 인터페이스
     * @return 유저 데이터 불러오는 URL 리턴
     * @throws 어떤 상황에서 예외가 발생!
     */
    public String socialGetUrl();

    // public String socialGetRefreshToken();
}