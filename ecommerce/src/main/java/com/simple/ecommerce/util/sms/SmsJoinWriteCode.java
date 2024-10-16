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

    @Autowired
    private SmsRepository smsRepository;

    @Override
    public RequestSmsDto dtoSetting(RequestSmsDto smsDto) {
        smsDto.getMessage().setSubject("회원가입을 위한 인증번호");
        String cert = ShaUtil.randomNumber();
        smsDto.getMessage().setText(String.format("[%s]", cert));
        smsDto.getCustom().setCode(cert);
        return smsDto;
    }

    @Override
    public void certCodeInsert(RequestSmsDto smsDto) {
        certCodeConverter converter = new certCodeConverter();
        SmsEntity entity = converter.toEntity(smsDto);
        smsRepository.save(entity);
    }

    
}
