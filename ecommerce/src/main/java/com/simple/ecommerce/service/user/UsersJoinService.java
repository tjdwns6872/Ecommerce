package com.simple.ecommerce.service.user;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.users.UsersJoinDto;

@Service
public interface UsersJoinService {

    int join(UsersJoinDto joinDto);
    
}
