package com.simple.ecommerce.serviceimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.users.UsersDetailConverter;
import com.simple.ecommerce.dto.users.UsersDetailResultDto;
import com.simple.ecommerce.entity.users.UsersEntity;
import com.simple.ecommerce.repository.users.UsersRepository;
import com.simple.ecommerce.service.user.UsersDetailService;

@Service
public class UsersDetailServiceImpl implements UsersDetailService{

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersDetailResultDto usersDetail(Integer id) {
        UsersDetailConverter converter = new UsersDetailConverter();
        UsersEntity entity = usersRepository.findByEcUsersId(id);
        UsersDetailResultDto dto = converter.toDto(entity);
        return dto;
    }
    
}
