package com.simple.ecommerce.serviceimpl.sms;

import java.lang.module.FindException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.ecommerce.component.sms.SmsWriteTypeFactory;
import com.simple.ecommerce.dto.sms.RequestSmsDto;
import com.simple.ecommerce.dto.sms.UserCheck;
import com.simple.ecommerce.exception.users.FindBadRequestException;
import com.simple.ecommerce.exception.users.FindNullException;
import com.simple.ecommerce.service.sms.SmsService;
import com.simple.ecommerce.util.StringUtils;
import com.simple.ecommerce.util.sms.AbstractSmsWriteType;
import com.simple.ecommerce.interfaces.sms.*;
import com.simple.ecommerce.interfaces.user.UserCheckInterface;
import com.simple.ecommerce.util.sms.SmsWriteType;

import jakarta.el.ELException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService{

    // 사용하는 SMS 기능 모듈을 불러오기 위한 코드
    @Autowired
    private SmsWriteTypeFactory smsWriteTypeFactory;

    /**
     * SMS 전송
     * @param smsDto
     * @return - smsId(String)
     * @throws Exception
     */
    @Override
    public String smsRequest(RequestSmsDto smsDto) throws Exception {
        String smsId = null;
        int count = 0;
        // 사용하는 SMS 기능 모듈을 불러오기 위한 코드
        AbstractSmsWriteType smsWrite = smsWriteTypeFactory.getSmsWriteType(smsDto.getCustom().getCustomType());
        // SMS 전송 시 필요한 헤더 데이터 세팅
        String headerStr = smsWrite.headerSetting();
        // SMS 전송 시 필요한 데이터 세팅
        RequestSmsDto dto = smsWrite.dtoSetting(smsDto);
        // 세팅된 헤더 데이터 dto에 삽입
        dto.getCustom().setCustomHeaderStr(headerStr);
        // SmsCertDb 인터페이스를 상속 받은 클래스는 해당 메소드 실행
        if(!StringUtils.isStringEmpty(smsDto.getCheck().getName())){
            if(smsWrite instanceof UserCheckInterface){
                count = ((UserCheckInterface)smsWrite).userCheck(smsDto.getCheck());
                if(count <= 0){
                    throw new FindNullException();
                }
            }
        }else{
            throw new FindBadRequestException();
        }
        // // SMS 전송
        smsWrite.smsWrite(dto);
        
        if(smsWrite instanceof SmsCertDb){
            // JPA를 통해 데이터를 DB에 삽입
            smsId = ((SmsCertDb)smsWrite).certCodeInsert(smsDto);
        }

        return smsId;
    }
}
