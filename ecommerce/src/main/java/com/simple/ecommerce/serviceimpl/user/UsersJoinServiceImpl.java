package com.simple.ecommerce.serviceimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.users.UsersJoinConverter;
import com.simple.ecommerce.converter.users.UsersJoinResultConverter;
import com.simple.ecommerce.dto.users.UsersJoinDto;
import com.simple.ecommerce.dto.users.UsersJoinResultDto;
import com.simple.ecommerce.entity.users.UsersEntity;
import com.simple.ecommerce.exception.users.JoinException;
import com.simple.ecommerce.repository.users.UsersRepository;
import com.simple.ecommerce.service.user.UsersJoinService;
import com.simple.ecommerce.util.ShaUtil;

@Service
public class UsersJoinServiceImpl implements UsersJoinService {

    @Autowired
    private UsersRepository usersRepository;

    //회원가입 메소드
    @Override
    public UsersJoinResultDto join(UsersJoinDto joinDto) {
        // 넘겨받은 DTO를 Entity로 변환하기 위한 인스턴스 생성
        UsersJoinConverter converter = new UsersJoinConverter();
        // DB삽입 후 데이터 삽입한 데이터를 저장할 Entity
        UsersEntity result = null;
        // result -> UsersJoinResultDto로 변환
        UsersJoinResultConverter resultCon = new UsersJoinResultConverter();
        try {
            // 넘겨받은 데이터에서 비밀번호를 sha256 인코딩을 통해 암호화 진행
            String pwdEncode = ShaUtil.sha256Encode(joinDto.getEcUsersPassword());
            // 암호화한 비밀번호 DTO에 입력
            joinDto.setEcUsersPassword(pwdEncode);
            // DTO를 Entity로 변환
            UsersEntity entity = converter.toEntity(joinDto);
            // DB에 데이터 저장 및 result에 데이터 삽입
            result = usersRepository.save(entity);
        } catch (Exception e) {
            throw new JoinException();
        }
        return resultCon.toDto(result);
    }
}