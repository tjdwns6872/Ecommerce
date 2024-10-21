package com.simple.ecommerce.converter.sms;

import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.entity.sms.SmsEntity;

public class certCodeConverter {
 
    public SmsEntity toEntity(RequestSmsDto smsDto){
        SmsEntity entity = SmsEntity.builder()
            .ecCertPhone(smsDto.getMessage().getTo())
            .ecCertCode(smsDto.getCustom().getCustomCode())
            .build();
        return entity;
    }
}
