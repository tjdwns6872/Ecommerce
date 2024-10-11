package com.simple.ecommerce.service.user;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.users.UsersDetailResultDto;

@Service
public interface UsersDetailService {    
    
    public UsersDetailResultDto usersDetail(Integer id);
}
