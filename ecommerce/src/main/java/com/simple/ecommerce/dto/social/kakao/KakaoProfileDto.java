package com.simple.ecommerce.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class KakaoProfileDto {

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("thumbnail_image_url")
    private String thumbnailImageUrl;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    @JsonProperty("is_default_image")
    private boolean isDefaultImage;

    @JsonProperty("is_default_nickname")
    private boolean isDefaultNickname;
}
