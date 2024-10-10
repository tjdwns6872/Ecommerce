package com.simple.ecommerce.service.user;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.users.UsersJoinDto;
import com.simple.ecommerce.dto.users.UsersJoinResultDto;

@Service
public interface UsersJoinService {

    /**
     * 회원가입 메소드
     * @param joinDto
     * @return UsersJoinResult
     */
    UsersJoinResultDto join(UsersJoinDto joinDto);
    
}
