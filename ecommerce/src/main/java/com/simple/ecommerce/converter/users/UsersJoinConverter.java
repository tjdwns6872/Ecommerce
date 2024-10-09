package com.simple.ecommerce.converter.users;

import com.simple.ecommerce.converter.Converter;
import com.simple.ecommerce.dto.users.UsersJoinDto;
import com.simple.ecommerce.entity.users.UsersEntity;

public class UsersJoinConverter {

    public UsersEntity toEntity(UsersJoinDto dto) {
        UsersEntity entity = UsersEntity.builder()
            .ecUsersEmail(dto.getEcUsersEmail())
            .ecUsersPassword(dto.getEcUsersPassword())
            .ecUsersName(dto.getEcUsersName())
            .ecUsersPhone(dto.getEcUsersPhone())
            .ecUsersBirthDate(dto.getEcUsersBirthDate())
            .ecUsersType(dto.getEcUsersType())
            .ecUsersIsSocialLogin(dto.getEcUsersIsSocialLogin())
            .ecUsersReferralCode(dto.getEcUsersReferralCode())
            .ecUsersCreatedAt(dto.getEcUsersCreatedAt())
            .ecUsersUpdatedAt(dto.getEcUsersUpdatedAt())
            .build();
        return entity;
    }
}
