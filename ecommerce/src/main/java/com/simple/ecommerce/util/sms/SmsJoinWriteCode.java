package com.simple.ecommerce.util.sms;

import org.springframework.stereotype.Component;

import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.util.ShaUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SmsJoinWriteCode extends AbstractSmsWriteType{

    @Override
    public RequestSmsDto dtoSetting(RequestSmsDto smsDto) {
        smsDto.getMessage().setSubject("회원가입을 위한 인증번호");
        String cert = ShaUtil.randomNumber();
        smsDto.getMessage().setText(String.format("[%s]", cert));
        return smsDto;
    }
    
}
