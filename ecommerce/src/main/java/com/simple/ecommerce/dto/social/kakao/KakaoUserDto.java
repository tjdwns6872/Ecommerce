package com.simple.ecommerce.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class KakaoUserDto {

    @JsonProperty("profile_nickname_needs_agreement")
    private boolean profileNicknameNeedsAgreement;

    @JsonProperty("profile_image_needs_agreement")
    private boolean profileImageNeedsAgreement;

    @JsonProperty("profile")
    private KakaoProfileDto profile;

    @JsonProperty("has_email")
    private boolean hasEmail;

    @JsonProperty("email_needs_agreement")
    private boolean emailNeedsAgreement;

    @JsonProperty("is_email_valid")
    private boolean isEmailValid;

    @JsonProperty("is_email_verified")
    private boolean isEmailVerified;

    @JsonProperty("email")
    private String email;
}