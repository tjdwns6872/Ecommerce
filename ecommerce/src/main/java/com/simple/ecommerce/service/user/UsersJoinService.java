package com.simple.ecommerce.service.user;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.users.UsersJoinDto;

@Service
public interface UsersJoinService {

    /**
     * 회원가입 메소드
     * @param joinDto
     * @return int
     */
    int join(UsersJoinDto joinDto);
    
}
