package com.simple.ecommerce.serviceimpl.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.component.sms.SmsWriteTypeFactory;
import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.service.sms.SmsService;
import com.simple.ecommerce.util.sms.AbstractSmsWriteType;
import com.simple.ecommerce.util.sms.SmsCertDb;
import com.simple.ecommerce.util.sms.SmsWriteType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService{

    @Autowired
    private SmsWriteTypeFactory smsWriteTypeFactory;

    @Override
    public String smsRequest(RequestSmsDto smsDto) throws Exception {
        AbstractSmsWriteType smsWrite = smsWriteTypeFactory.getSmsWriteType(smsDto.getCustom().getType());
        String headerStr = smsWrite.headerSetting();
        log.info("\n\n{}\n\n", headerStr);
        log.info("\n\n{}\n\n", smsDto.toString());
        RequestSmsDto dto = smsWrite.dtoSetting(smsDto);
        log.info("\n\n{}\n\n", dto.toString());
        if(smsWrite instanceof SmsCertDb){
            ((SmsCertDb)smsWrite).certCodeInsert(smsDto);
        }
        // smsWrite.smsWrite(dto);

        return null;
    }
}
