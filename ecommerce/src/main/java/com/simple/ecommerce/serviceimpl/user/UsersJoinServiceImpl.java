package com.simple.ecommerce.serviceimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.users.UsersJoinConverter;
import com.simple.ecommerce.dto.users.UsersJoinDto;
import com.simple.ecommerce.entity.users.UsersEntity;
import com.simple.ecommerce.repository.users.UsersRepository;
import com.simple.ecommerce.service.user.UsersJoinService;

@Service
public class UsersJoinServiceImpl implements UsersJoinService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public int join(UsersJoinDto joinDto) {
        UsersJoinConverter converter = new UsersJoinConverter();
        UsersEntity entity = converter.toEntity(joinDto);
        usersRepository.save(entity);
        return 0;
    }
}