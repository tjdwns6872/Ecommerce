package com.simple.ecommerce.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class UserJwtDto {
    
    private Integer ecUsersId;
    private String ecUsersEmail;
    private String ecUsersName;
}
