package com.simple.ecommerce.entity.users;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="EC_USERS")
public class UsersEntity {
    
    //회원 고유식별자
    @Id
    @Column(name="EC_USERS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ecUsersId;

    //회원 이메일
    @Column(name="EC_USERS_EMAIL")
    private String ecUsersEmail;

    //회원 비밀번호
    @Column(name="EC_USERS_PASSWORD")
    private String ecUsersPassword;

    //회원 이름
    @Column(name="EC_USERS_NAME")
    private String ecUsersName;

    //회원 전화번호
    @Column(name="EC_USERS_PHONE")
    private String ecUsersPhone;

    //회원 생년월일
    @Column(name="EC_USERS_BIRTH_DATE")
    private Date ecUsersBirthDate;

    //회원 타입
    @Column(name="EC_USERS_TYPE")
    private Integer ecUsersType;

    //소셜로그인 여부
    @Column(name="EC_USERS_IS_SOCIAL_LOGIN")
    private boolean ecUsersIsSocialLogin;

    //추천인코드
    @Column(name="EC_USERS_REFERRAL_CODE")
    private String ecUsersReferralCode;

    //회원 생성일
    @Column(name="EC_USERS_CREATED_AT")
    private Date ecUsersCreatedAt;

    //회원 수정일
    @Column(name="EC_USERS_UPDATED_AT")
    private Date ecUsersUpdatedAt;
}