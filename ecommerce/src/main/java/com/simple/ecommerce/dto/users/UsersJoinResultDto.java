package com.simple.ecommerce.dto.users;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;

@Data
@Builder
public class UsersJoinResultDto {
    
    //회원 고유식별자
    private Integer ecUsersId;

    //회원 이메일
    private String ecUsersEmail;

    //회원 이름
    private String ecUsersName;

    //회원 생성일
    private Date ecUsersCreatedAt;
}
