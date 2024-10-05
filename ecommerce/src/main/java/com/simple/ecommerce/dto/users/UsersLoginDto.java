package com.simple.ecommerce.dto.users;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class UsersLoginDto {
    
    //회원 이메일
    private String ecUsersEmail;
    //회원 비밀번호
    private String ecUsersPassword;
}
