package com.simple.ecommerce.dto.social;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SocialConnectDto {
 
    private String grantType;
    private String state;
    private String code;
}
