package com.simple.ecommerce.entity.users;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
// import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="EC_USERS")
public class UsersEntity {
    
    //회원 고유식별자
    @Id
    private Integer ecUsersId;

    //회원 이메일
    private String ecUsersEmail;

    //회원 비밀번호
    private String ecUsersPassword;

    //회원 이름
    private String ecUsersName;

    //회원 전화번호
    private String ecUsersPhone;

    //회원 생년월일
    private Date ecUsersBirthDate;

    //회원 타입
    private Integer ecUsersType;

    //소셜로그인 여부
    private Boolean ecUsersIsSocialLogin;

    //추천인코드
    private String ecUsersReferralCode;

    //회원 생성일
    private Date ecUsersCreatedAt;

    //회원 수정일
    private Date ecUsersUpdatedAt;
}