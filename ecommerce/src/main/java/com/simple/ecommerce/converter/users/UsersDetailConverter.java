package com.simple.ecommerce.converter.users;

import com.simple.ecommerce.dto.users.UsersDetailResultDto;
import com.simple.ecommerce.entity.users.UsersEntity;

public class UsersDetailConverter {
    
    public UsersEntity toEntity(UsersDetailResultDto dto) {
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

    public UsersDetailResultDto toDto(UsersEntity entity) {
        UsersDetailResultDto dto = UsersDetailResultDto.builder()
            .ecUsersEmail(entity.getEcUsersEmail())
            .ecUsersPassword(entity.getEcUsersPassword())
            .ecUsersName(entity.getEcUsersName())
            .ecUsersPhone(entity.getEcUsersPhone())
            .ecUsersBirthDate(entity.getEcUsersBirthDate())
            .ecUsersType(entity.getEcUsersType())
            .ecUsersIsSocialLogin(entity.isEcUsersIsSocialLogin())
            .ecUsersReferralCode(entity.getEcUsersReferralCode())
            .ecUsersCreatedAt(entity.getEcUsersCreatedAt())
            .ecUsersUpdatedAt(entity.getEcUsersUpdatedAt())
            .build();
        return dto;
    }
}
