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

    // 사용하는 SMS 기능 모듈을 불러오기 위한 코드
    @Autowired
    private SmsWriteTypeFactory smsWriteTypeFactory;

    /**
     * SMS 전송
     * @param smsDto
     * @return - 수정 예정
     * @throws Exception
     */
    @Override
    public String smsRequest(RequestSmsDto smsDto) throws Exception {
        // 사용하는 SMS 기능 모듈을 불러오기 위한 코드
        AbstractSmsWriteType smsWrite = smsWriteTypeFactory.getSmsWriteType(smsDto.getCustom().getCustomType());
        // SMS 전송 시 필요한 헤더 데이터 세팅
        String headerStr = smsWrite.headerSetting();
        // SMS 전송 시 필요한 데이터 세팅
        RequestSmsDto dto = smsWrite.dtoSetting(smsDto);
        // 세팅된 헤더 데이터 dto에 삽입
        dto.getCustom().setCustomHeaderStr(headerStr);
        // SmsCertDb 인터페이스를 상속 받은 클래스는 해당 메소드 실행
        if(smsWrite instanceof SmsCertDb){
            // JPA를 통해 데이터를 DB에 삽입
            ((SmsCertDb)smsWrite).certCodeInsert(smsDto);
        }
        // SMS 전송
        smsWrite.smsWrite(dto);

        return null;
    }
}
