package com.simple.ecommerce.util.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simple.ecommerce.converter.sms.certCodeConverter;
import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.entity.sms.SmsEntity;
import com.simple.ecommerce.repository.sms.SmsRepository;
import com.simple.ecommerce.util.ShaUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SmsJoinWriteCode extends AbstractSmsWriteType implements SmsCertDb{

    // 데이터를 DB에 저장하기 위해 사용되는 모듈 선언
    @Autowired
    private SmsRepository smsRepository;

    // SMS 전송 시 사용할 데이터 셋팅
    @Override
    public RequestSmsDto dtoSetting(RequestSmsDto smsDto) {
        // SMS TITLE 세팅
        smsDto.getMessage().setSubject("회원가입을 위한 인증번호");
        // 인증코드 생성
        String cert = ShaUtil.randomNumber();
        // SMS 내용 세팅
        smsDto.getMessage().setText(String.format("[%s]", cert));
        // 인증코드 저장
        smsDto.getCustom().setCode(cert);
        // SMS 전송할 때 사용되는 타입 선언(coolSMS에서 정해진 타입)
        smsDto.getMessage().setType("SMS");
        // 세팅된 DTO 리턴
        return smsDto;
    }

    // 전송한 데이터 DB에 저장
    @Override
    public void certCodeInsert(RequestSmsDto smsDto) {
        // DTO -> Entity로 전환하기 위한 인스턴스 생성
        certCodeConverter converter = new certCodeConverter();
        // DTO를 Entity로 변환
        SmsEntity entity = converter.toEntity(smsDto);
        // DB에 저장
        smsRepository.save(entity);
    }

    
}
