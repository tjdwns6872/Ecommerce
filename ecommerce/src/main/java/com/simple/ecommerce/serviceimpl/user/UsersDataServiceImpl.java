package com.simple.ecommerce.serviceimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.users.UsersDataConverter;
import com.simple.ecommerce.dto.users.UsersDataResultDto;
import com.simple.ecommerce.dto.users.UsersFindDto;
import com.simple.ecommerce.entity.users.UsersEntity;
import com.simple.ecommerce.exception.users.FindBadRequestException;
import com.simple.ecommerce.exception.users.FindNullException;
import com.simple.ecommerce.repository.users.UsersRepository;
import com.simple.ecommerce.service.mail.MailComponent;
import com.simple.ecommerce.service.mail.SendMailForm;
import com.simple.ecommerce.service.sms.SmsService;
import com.simple.ecommerce.service.user.UsersDataService;
import com.simple.ecommerce.util.ShaUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersDataServiceImpl implements UsersDataService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MailComponent mailComponent;

    @Override
    public String usersData(UsersFindDto findDto) {
        //회원정보를 찾은 후 데이터를 보관할 Entity 생성
        UsersEntity entity = new UsersEntity();
        String result = null;
        try {
            //타입에 따라 데이터 조회 필터링 변경
            if(findDto.getType().equals("emailFind")){
                //사용자가 이메일을 찾을 경우 이름과 전화번호를 통해 찾음
                entity = usersRepository.findByEcUsersPhoneAndEcUsersName(findDto.getPhone(), findDto.getName());
                //조회 후 이메일을 리턴할 변수에 저장
                result = entity.getEcUsersEmail();
            }else if(findDto.getType().equals("passwordFind")){
                //비밀번호를 찾을 경우 이메일과 전화번호를 통해 찾음
                entity = usersRepository.findByEcUsersEmailAndEcUsersPhone(findDto.getEmail(), findDto.getPhone());
                //임시 비밀번호 발급
                String password = ShaUtil.randomString();
                //발급 받은 임시비밀번호 entity에 저장
                entity.setEcUsersPassword(ShaUtil.sha256Encode(password));
                //임시 비밀번호로 해당 사용자 비밀번호 업데이트
                usersRepository.save(entity);
                //임시 비밀번호를 전송할 이메일 세팅
                SendMailForm form = SendMailForm.builder()
                //사용자 이메일
                .to(entity.getEcUsersEmail())
                //메일 타이틀 
                .subject("임시 비밀번호 발송")
                //임시 비밀번호
                .text(password)
                .build();
                //메일 전송
                mailComponent.sendSimpleMessage(form);
                //전송 후 사용자한테 안내
                result = entity.getEcUsersEmail()+"로 임시 비밀번호 발송했습니다.";
            }else{
                throw new FindBadRequestException();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new FindNullException();
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public UsersDataResultDto usersData(Integer id) {
        UsersDataConverter converter = new UsersDataConverter();
        UsersEntity entity = usersRepository.findByEcUsersId(id);
        UsersDataResultDto dto = converter.toDto(entity);
        return dto;
    }
    
}
