package com.simple.ecommerce.service.user;

import org.springframework.stereotype.Service;

import com.simple.ecommerce.dto.users.UsersDataResultDto;
import com.simple.ecommerce.dto.users.UsersFindDto;

@Service
public interface UsersDataService {    
    
    /**
     * 아이디 혹은 비밀번호 찾기 진행 시 사용하는 메소드
     * @param findDto
     * @return String
     */
    public String usersData(UsersFindDto findDto);

    /**
     * 마이페이지 또는 사용자 데이터를 불러올 때 사용하는 메소드
     * @param id
     * @return UsersDataResultDto
     */
    public UsersDataResultDto usersData(Integer id);
}
