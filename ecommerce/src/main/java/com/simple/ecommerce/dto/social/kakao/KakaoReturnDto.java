package com.simple.ecommerce.dto.social.kakao;

import java.sql.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class KakaoReturnDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("connected_at")
    private Date connectedAt;

    @JsonProperty("properties")
    private KakaoPropertiesDto properties;

    @JsonProperty("kakao_account")
    private KakaoUserDto kakaoAccount;
}
