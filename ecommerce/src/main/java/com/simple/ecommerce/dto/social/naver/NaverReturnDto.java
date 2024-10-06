package com.simple.ecommerce.dto.social.naver;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 소셜로그인(Naver) 유저데이터 요청 시 오는 데이터 변환을 위한 DTO
 */
@Data @NoArgsConstructor
public class NaverReturnDto {

    // 상태코드
    @JsonProperty("resultcode")
    private String resultCode;

    // 상태 메시지
    @JsonProperty("message")
    private String message;

    // 유저데이터
    @JsonProperty("response")
    private NaverUserDto response;
}
