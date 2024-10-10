package com.simple.ecommerce.converter.users;

import com.simple.ecommerce.dto.users.UsersJoinResultDto;
import com.simple.ecommerce.entity.users.UsersEntity;

public class UsersJoinResultConverter {
    
    public UsersJoinResultDto toDto(UsersEntity entity){
        return UsersJoinResultDto.builder()
            .ecUsersId(entity.getEcUsersId())
            .ecUsersName(entity.getEcUsersName())
            .ecUsersName(entity.getEcUsersName())
            .ecUsersCreatedAt(entity.getEcUsersCreatedAt())
            .build();
    }
}
