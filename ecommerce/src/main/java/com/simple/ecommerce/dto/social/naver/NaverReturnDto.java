package com.simple.ecommerce.dto.social.naver;

/**
 * 소셜로그인(Naver) 유저데이터 요청 시 오는 데이터 변환을 위한 DTO
 */
public class NaverReturnDto {
    private String resultCode;
    private String message;
    private NaverUserDto response;
}
