package com.simple.ecommerce.util.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simple.ecommerce.converter.sms.certCodeConverter;
import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.dto.sms.UserCheck;
import com.simple.ecommerce.entity.sms.SmsEntity;
import com.simple.ecommerce.entity.users.UsersEntity;
import com.simple.ecommerce.interfaces.sms.SmsCertDb;
import com.simple.ecommerce.interfaces.user.UserCheckInterface;
import com.simple.ecommerce.repository.sms.SmsRepository;
import com.simple.ecommerce.repository.users.UsersRepository;
import com.simple.ecommerce.util.ShaUtil;

@Component
public class SmsFindWriteCode extends AbstractSmsWriteType implements SmsCertDb, UserCheckInterface{

    // 데이터를 DB에 저장하기 위해 사용되는 모듈 선언
    @Autowired
    private SmsRepository smsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public RequestSmsDto dtoSetting(RequestSmsDto smsDto) {
        String subTitle = null;
        if(smsDto.getCustom().getCustomType().equals("emailFind")){
            subTitle = "이메일을";
        }else{
            subTitle = "비밀번호를";
        }
        // SMS TITLE 세팅
        // smsDto.getMessage().setSubject(subTitle+" 찾기 위한 인증번호");
        // 인증코드 생성
        String cert = ShaUtil.randomNumber();
        // SMS 내용 세팅
        smsDto.getMessage().setText(String.format("[%s]", cert));
        // 인증코드 저장
        smsDto.getCustom().setCustomCode(cert);
        // SMS 전송할 때 사용되는 타입 선언(coolSMS에서 정해진 타입)
        // smsDto.getMessage().setType("SMS");
        // 세팅된 DTO 리턴
        return smsDto;
    }

    @Override
    public String certCodeInsert(RequestSmsDto smsDto) {
        // DTO -> Entity로 전환하기 위한 인스턴스 생성
        certCodeConverter converter = new certCodeConverter();
        // DTO를 Entity로 변환
        SmsEntity entity = converter.toEntity(smsDto);
        // DB에 저장
        SmsEntity result = smsRepository.save(entity);

        return String.valueOf(result.getEcCertId());
    }

    @Override
    public Integer userCheck(UserCheck userCheck) {
        UsersEntity entity = new UsersEntity();

        int count = 0;

        entity.setEcUsersName(userCheck.getName());
        entity.setEcUsersPhone(userCheck.getPhone());
        if(userCheck.getType().equals("emailFind")){
            entity.setEcUsersEmail(userCheck.getEmail());
            count = usersRepository.countByEcUsersNameAndEcUsersPhone("우성준","010-7557-9897");
        }else{
            count = usersRepository.countByEcUsersPhoneAndEcUsersNameAndEcUsersEmail("","","");
        }
        return count;
    }
    
}
