package com.simple.ecommerce.dto.social.naver;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class NaverUserDto {

    // 유저 식별 아이디
    @JsonProperty("id")
    private String id;
    
    // 유저 닉네임
    @JsonProperty("nickname")
    private String nickName;

    // 유저 프로필 사진
    @JsonProperty("profile_image")
    private String profileImage;

    // 유저 나이대
    @JsonProperty("age")
    private String age;

    // 유저 성별
    @JsonProperty("gender")
    private char gender;

    // 유저 이메일(*중요)
    @JsonProperty("email")
    private String email;

    // 유저 휴대폰 번호
    @JsonProperty("mobile")
    private String mobile;

    // 유저 나라번호 포함한 전화번호
    @JsonProperty("mobile_e164")
    private String mobileE164;

    // 유저 이름
    @JsonProperty("name")
    private String name;

    // 유저 생일
    @JsonProperty("birthday")
    private String birthDay;

    // 유저 생년(태어난 연도)
    @JsonProperty("birthyear")
    private String birthYear;
    
}