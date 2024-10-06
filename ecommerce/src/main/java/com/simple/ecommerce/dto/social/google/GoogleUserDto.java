package com.simple.ecommerce.dto.social.google;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class GoogleUserDto {

    @JsonProperty("azp")
    private String azp;

    @JsonProperty("aud")
    private String aud;

    @JsonProperty("sub")
    private String sub;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("exp")
    private String exp;

    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_verified")
    private String emailVerified;

    @JsonProperty("access_type")
    private String accessType;
}
