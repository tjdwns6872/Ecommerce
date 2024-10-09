package com.simple.ecommerce.serviceimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.users.UsersJoinConverter;
import com.simple.ecommerce.dto.users.UsersJoinDto;
import com.simple.ecommerce.entity.users.UsersEntity;
import com.simple.ecommerce.repository.users.UsersRepository;
import com.simple.ecommerce.service.user.UsersJoinService;
import com.simple.ecommerce.util.ShaUtil;

@Service
public class UsersJoinServiceImpl implements UsersJoinService {

    @Autowired
    private UsersRepository usersRepository;

    //회원가입 메소드
    @Override
    public int join(UsersJoinDto joinDto) {
        // 넘겨받은 DTO를 Entity로 변환하기 위한 인스턴스 생성
        UsersJoinConverter converter = new UsersJoinConverter();
        // 넘겨받은 데이터에서 비밀번호를 sha256 인코딩을 통해 암호화 진행
        String pwdEncode = ShaUtil.sha256Encode(joinDto.getEcUsersPassword());
        // 암호화한 비밀번호 DTO에 입력
        joinDto.setEcUsersPassword(pwdEncode);
        // DTO를 Entity로 변환
        UsersEntity entity = converter.toEntity(joinDto);
        usersRepository.save(entity);
        return 0;
    }
}