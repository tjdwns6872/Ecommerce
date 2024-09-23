package com.simple.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SocialTokenDto {
    
    private String accessToken;
    private String refreshToken;  
    private String tokenType; 
}
