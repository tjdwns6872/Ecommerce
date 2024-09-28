package com.simple.ecommerce.dto.social;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SocialTokenDto {
    
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType; 

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_dexcription")
    private String errorDexcription;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("refresh_token_expires_in")
    private String refreshTokenExpiresIn;

    @JsonProperty("id_token")
    private String idToken;
}
// access_token	string	접근 토큰, 발급 후 expires_in 파라미터에 설정된 시간(초)이 지나면 만료됨
// refresh_token	string	갱신 토큰, 접근 토큰이 만료될 경우 접근 토큰을 다시 발급받을 때 사용
// token_type	string	접근 토큰의 타입으로 Bearer와 MAC의 두 가지를 지원
// expires_in	integer	접근 토큰의 유효 기간(초 단위)
// error	string	에러 코드
// error_description	string	에러 메시지

// access_token	string	접근 토큰, 발급 후 expires_in 파라미터에 설정된 시간(초)이 지나면 만료됨
// token_type	string	접근 토큰의 타입으로 Bearer와 MAC의 두 가지를 지원
// expires_in	integer	접근 토큰의 유효 기간(초 단위)
// error	string	에러 코드
// error_description	string	에러 메시지
