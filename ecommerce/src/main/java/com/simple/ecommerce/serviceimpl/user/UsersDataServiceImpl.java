package com.simple.ecommerce.serviceimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.converter.users.UsersDataConverter;
import com.simple.ecommerce.dto.users.UsersDataResultDto;
import com.simple.ecommerce.dto.users.UsersFindDto;
import com.simple.ecommerce.entity.users.UsersEntity;
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
        UsersEntity entity = new UsersEntity();
        String result = null;
        try {
            if(findDto.getType().equals("findEmail")){
                entity = usersRepository.findByEcUsersPhoneAndEcUsersName(findDto.getPhone(), findDto.getName());
                result = entity.getEcUsersEmail();
            }else{
                entity = usersRepository.findByEcUsersEmailAndEcUsersPhone(findDto.getEmail(), findDto.getPhone());
                log.info("\n\n{}\n\n", entity.toString());
                String password = ShaUtil.randomString();
                entity.setEcUsersPassword(password);
                usersRepository.save(entity);
                SendMailForm form = SendMailForm.builder()
                .to(entity.getEcUsersEmail())
                .subject("임시 비밀번호 발송")
                .text(password)
                .build();
                mailComponent.sendSimpleMessage(form);
                result = entity.getEcUsersEmail()+"로 임시 비밀번호 발송했습니다.";
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
